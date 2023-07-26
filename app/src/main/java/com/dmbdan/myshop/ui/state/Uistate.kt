package com.dmbdan.myshop.ui.state

import com.dmbdan.myshop.domain.model.ProductResponseItem

data class Uistate(
    val isLoading : Boolean = false,
    val error : String? = null,
    val products : ArrayList<ProductResponseItem> = ArrayList()
)
