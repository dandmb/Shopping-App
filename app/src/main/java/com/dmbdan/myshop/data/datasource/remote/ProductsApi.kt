package com.dmbdan.myshop.data.datasource.remote

import com.dmbdan.myshop.domain.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("/products")
    suspend fun getProducts() : Response<ProductResponse>
}