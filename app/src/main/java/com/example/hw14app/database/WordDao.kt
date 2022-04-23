package com.example.hw14app.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hw14app.model.Word


@Dao
interface WordDao {

    @Query("SELECT * FROM Word")
    fun getAllWords(): List<Word>

    @Insert
    fun insertAll(vararg word: Word)

    @Delete
    fun delete(vararg word: Word)

    @Query("SELECT * FROM Word WHERE persian IN (:persian)")
    fun getWordWithPersian(persian: String): Word

    @Query("SELECT * FROM Word WHERE english IN (:english)")
    fun getWordWithEnglish(english: String): Word

    @Query("SELECT * FROM Word WHERE id IN (:id)")
    fun getWordWithID(id: Int): Word

    @Query("SELECT COUNT (*) FROM Word")
    fun getWordsCount(): LiveData<Int>

    @Query("SELECT COUNT (*) FROM Word")
    fun getWordsCount2(): Int

    @Query("SELECT MIN(id) FROM Word")
    fun getMinId(): Int?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(word: Word?)
}