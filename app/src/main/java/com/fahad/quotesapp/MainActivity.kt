package com.fahad.quotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.fahad.quotesapp.api.QuoteService
import com.fahad.quotesapp.api.RetrofitHelper
import com.fahad.quotesapp.databinding.ActivityMainBinding
import com.fahad.quotesapp.repository.QuoteRepository
import com.fahad.quotesapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as QuoteApplication).repository
        mainViewModel = MainViewModel(repository)

        mainViewModel.quotes.observe(this, Observer {
            binding.textView.text = it.results.size.toString()
        })
    }
}