package com.fahad.quotesapp

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import com.fahad.quotesapp.api.QuoteService
import com.fahad.quotesapp.api.RetrofitHelper
import com.fahad.quotesapp.db.QuoteDatabase
import com.fahad.quotesapp.repository.QuoteRepository
import com.fahad.quotesapp.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication : Application() {
    lateinit var repository: QuoteRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        backgroundWorker()
    }

    private fun backgroundWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val workerReq = PeriodicWorkRequest.Builder(
            QuoteWorker::class.java,
            30,
            TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val quoteDatabase = QuoteDatabase.getDatabaseInstance(applicationContext)
        repository = QuoteRepository(quoteService, quoteDatabase)
    }
}