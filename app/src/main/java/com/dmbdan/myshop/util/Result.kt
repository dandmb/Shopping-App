package com.dmbdan.myshop.util



sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {

    // We'll wrap our data in this 'Success'
    // class in case of success response from api
    class SUCCESS<T>(data: T) : Result<T>(data)

    // We'll pass error message wrapped in this 'Error'
    // class to the UI in case of failure response
    class ERROR<T>(message: String,data: T? = null) : Result<T>(data, message)

    // We'll just pass object of this Loading
    // class, just before making an api call
    class LOADING<T> : Result<T>()
}