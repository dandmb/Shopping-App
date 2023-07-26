package com.dmbdan.myshop.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmbdan.myshop.data.repository.ProductRepository
import com.dmbdan.myshop.ui.state.Uistate
import com.dmbdan.myshop.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel(){

    val uistate = mutableStateOf(Uistate())

    init {
        getProducts()
    }

    fun getProducts(){
        viewModelScope.launch {
            repository.getProducts().collect{result->
                when(result){
                    is Result.LOADING ->{
                        uistate.value = Uistate(isLoading = true)
                    }
                    is Result.SUCCESS -> {
                        uistate.value = Uistate(isLoading = false, products = result.data!!)
                        println(uistate.value)
                    }

                    else -> {
                        uistate.value = Uistate(error = result.message)
                    }
                }

            }
        }
    }
}