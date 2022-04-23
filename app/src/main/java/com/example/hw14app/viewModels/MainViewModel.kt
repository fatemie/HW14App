package com.example.hw14app.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hw14app.model.Word
import com.example.hw14app.repository.DictionaryRepository

class MainViewModel(app: Application): AndroidViewModel(app){

    var dictionary = listOf<Word>()
    val wordCountLiveData = MutableLiveData<Int>(0)

    init{
        DictionaryRepository.initDB(app.applicationContext)
        dictionary = DictionaryRepository.getAllWords()!!
        wordCountLiveData.value = DictionaryRepository.getWordsCount2()!!
    }

    fun addWord(word: Word) {
        DictionaryRepository.insertWord(word)
    }

    fun getWord(wordId : Int) : Word? {
        return DictionaryRepository.getWordWithID(wordId)
    }
}