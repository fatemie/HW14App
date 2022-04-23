package com.example.hw14app.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.hw14app.model.Word
import com.example.hw14app.repository.DictionaryRepository

class MainViewModel(app: Application): AndroidViewModel(app){

    init{
        DictionaryRepository.initDB(app.applicationContext)
    }
    fun goToWordDetail(word: Word) {

    }

    fun addWord(word: Word) {
        DictionaryRepository.insertWord(word)
    }
}