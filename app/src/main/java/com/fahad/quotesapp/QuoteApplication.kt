package com.fahad.quotesapp

import android.app.Application
import com.fahad.quotesapp.api.QuoteService
import com.fahad.quotesapp.api.RetrofitHelper
import com.fahad.quotesapp.db.QuoteDatabase
import com.fahad.quotesapp.repository.QuoteRepository

class QuoteApplication : Application() {
    lateinit var repository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val quoteDatabase = QuoteDatabase.getDatabaseInstance(applicationContext)
        repository = QuoteRepository(quoteService, quoteDatabase)
    }
}