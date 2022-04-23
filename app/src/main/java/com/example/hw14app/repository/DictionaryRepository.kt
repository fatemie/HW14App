package com.example.hw14app.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.hw14app.database.AppDatabase
import com.example.hw14app.database.WordDao
import com.example.hw14app.model.Word

object DictionaryRepository {
    val dictionary = arrayListOf<Word>()

    var db: AppDatabase?=null
    var dao: WordDao?=null

    fun initDB(context: Context){
        db= AppDatabase.getAppDatabase(context)
        dao=db?.wordDao()
    }

    fun insertWord( word: Word){
        dao?.insertAll(word)
    }

    fun getWordWithPersian(persian : String) : Word?{
        return dao?.getWordWithPersian(persian)
    }

    fun getWordWithEnglish(english : String) : Word?{
        return dao?.getWordWithEnglish(english)
    }

    fun getWordsCount(): LiveData<Int>? {
        return dao?.getWordsCount()
    }

    fun getWordsCount2(): Int?{
        return dao?.getWordsCount2()
    }

    fun getMinId(): Int? {
        return dao?.getMinId()
    }
}