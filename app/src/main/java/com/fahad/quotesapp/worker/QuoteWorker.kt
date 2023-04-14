package com.fahad.quotesapp.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.fahad.quotesapp.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(
    private val context: Context,
    private val params: WorkerParameters
) : Worker(context, params) {
    // do work method overriding
    override fun doWork(): Result {
        val repository = (context as QuoteApplication).repository

        CoroutineScope(Dispatchers.IO).launch {
           repository.backgroundTask()
        }
        return Result.success()
    }
}