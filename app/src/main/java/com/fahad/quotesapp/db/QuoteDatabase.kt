package com.fahad.quotesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [com.fahad.quotesapp.models.Result::class], version = 1)
abstract class QuoteDatabase : RoomDatabase() {
    abstract fun getQuoteDao() : QuoteDao

    companion object {
        private var INSTANCE: QuoteDatabase? = null
        fun getDatabaseInstance(context: Context) : QuoteDatabase {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context,
                        QuoteDatabase::class.java,
                        "quote_db")
                        .build()
                }

            }
            return INSTANCE!!
        }
    }
}


