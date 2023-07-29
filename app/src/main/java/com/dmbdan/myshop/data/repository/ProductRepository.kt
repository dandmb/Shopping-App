package com.dmbdan.myshop.data.repository


import com.dmbdan.myshop.data.datasource.locale.ProductDao
import com.dmbdan.myshop.data.datasource.remote.ProductsApi
import com.dmbdan.myshop.domain.model.toProductResponse
import com.dmbdan.myshop.util.Result
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val dao: ProductDao,
    private val api: ProductsApi
) {
    fun getProducts() = flow {
        emit(Result.LOADING())
        val remoteProducts = api.getProducts().body()
        remoteProducts?.forEach {
            dao.addProduct(it)
        }
        emit(Result.SUCCESS(remoteProducts))
    }.catch { error ->
        emit(Result.LOADING())
        val localData = dao.getProducts()

        coroutineScope {
            launch {
                localData.collect {
                    emit(Result.ERROR(it.toProductResponse(), error.message!!))
                }
            }
        }
    }
}