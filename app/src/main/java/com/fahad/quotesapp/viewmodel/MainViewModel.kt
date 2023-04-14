package com.fahad.quotesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahad.quotesapp.models.QuoteList
import com.fahad.quotesapp.repository.QuoteRepository
import com.fahad.quotesapp.repository.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository): ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }

    val quotes: LiveData<Resource<QuoteList>>
    get() = repository.quotes
}