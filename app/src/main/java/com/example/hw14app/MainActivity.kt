package com.example.hw14app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hw14app.databinding.ActivityMainBinding
import com.example.hw14app.repository.DictionaryRepository
import com.example.hw14app.viewModels.MainViewModel
import com.example.myapp.WordAdapter

class MainActivity : AppCompatActivity() {
    private val vModel : MainViewModel by viewModels()
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}