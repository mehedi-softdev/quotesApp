package com.fahad.quotesapp.api

import com.fahad.quotesapp.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("quotesd")
    suspend fun getQuotes(@Query("page") page: Int) : Response<QuoteList>
}