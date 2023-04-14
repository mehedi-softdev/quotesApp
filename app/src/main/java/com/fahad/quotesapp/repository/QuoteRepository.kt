package com.fahad.quotesapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fahad.quotesapp.Utils.Network
import com.fahad.quotesapp.api.QuoteService
import com.fahad.quotesapp.db.QuoteDatabase
import com.fahad.quotesapp.models.QuoteList

class QuoteRepository(private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase) {
    // live data for live action
    private val quotesLiveData = MutableLiveData<QuoteList>()

    val quotes : LiveData<QuoteList>
    get() = quotesLiveData

    suspend fun getQuotes(page: Int) {

        if(Network.isInternetAvailable()) {
            val result = quoteService.getQuotes(page)
            if(result?.body() != null) {
                quoteDatabase.getQuoteDao().insertQuote(result.body()!!.results)
                quotesLiveData.postValue(result.body())
            }
        } else {
            // fetch data from local storage
            val result = quoteDatabase.getQuoteDao().getQuotes()
            val quoteList = QuoteList(
                1,
                1,
                page,
                result,
                1,
                1
            )
            quotesLiveData.postValue(quoteList)
        }


    }

    suspend fun backgroundTask() {
        val randomPageNumber = (Math.random() * 10).toInt()
        val result = quoteService.getQuotes(randomPageNumber)
        if(result?.body() != null) {
            // store to local storage
            quoteDatabase.getQuoteDao().insertQuote(result.body()!!.results)
        }
    }
}