package com.fahad.quotesapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDao {
    @Insert
    suspend fun insertQuote(quotes: List<com.fahad.quotesapp.models.Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes() : List<com.fahad.quotesapp.models.Result>
}