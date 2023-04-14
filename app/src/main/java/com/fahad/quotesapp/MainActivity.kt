package com.fahad.quotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.fahad.quotesapp.api.QuoteService
import com.fahad.quotesapp.api.RetrofitHelper
import com.fahad.quotesapp.databinding.ActivityMainBinding
import com.fahad.quotesapp.repository.QuoteRepository
import com.fahad.quotesapp.repository.Resource
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
            when(it) {
                is Resource.Loading -> {
                    Toast.makeText(this, "Data is loading...", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    it.data?.let {
                        binding.textView.text = it.results.size.toString()
                    }
                }
                is Resource.Error -> {
                    it.errorMessage.let {
                        Toast.makeText(
                            this,
                            it,
                            Toast.LENGTH_SHORT
                            ).show()
                    }
                }
            }
        })
    }
}