package com.fahad.quotesapp.repository

import com.fahad.quotesapp.models.QuoteList

// Generic class
/*
    Sealed class can have multiple states
    where enum just plays with values
 */
sealed class Resource<T>(val data: T? = null,
    val errorMessage: String? = null) {

    class Loading<T> : Resource<T>()
    class Success<T>(data: T? = null) : Resource<T>(data = data)
    class Error<T>(errorMessage: String) : Resource<T>(errorMessage = errorMessage)
}
