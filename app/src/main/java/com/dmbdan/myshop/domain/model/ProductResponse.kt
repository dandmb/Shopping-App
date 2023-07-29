package com.dmbdan.myshop.domain.model



class ProductResponse(val data:List<ProductResponseItem> = listOf()) : ArrayList<ProductResponseItem>()

fun List<ProductResponseItem>.toProductResponse() : ProductResponse {
    return ProductResponse(this)
}