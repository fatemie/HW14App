package com.example.hw14app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.hw14app.repository.DictionaryRepository
import com.example.hw14app.viewModels.MainViewModel
import com.example.myapp.WordAdapter

class MainActivity : AppCompatActivity() {
    private val vModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



}