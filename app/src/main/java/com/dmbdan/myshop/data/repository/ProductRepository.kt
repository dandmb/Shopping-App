package com.dmbdan.myshop.data.repository

import com.dmbdan.myshop.data.datasource.locale.ProductDao
import com.dmbdan.myshop.data.datasource.remote.ProductsApi
import com.dmbdan.myshop.util.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val dao: ProductDao,
    private val api: ProductsApi
) {
    fun getProducts() = flow{
        emit(Result.LOADING())
        val remoteProducts=api.getProducts().body()
        emit(Result.SUCCESS(remoteProducts))
        //dao.addProduct(remoteProducts)
    }.catch { error->
        emit(Result.ERROR(error.message!!))
    }
}