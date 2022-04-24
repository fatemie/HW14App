package com.example.hw14app.viewModels

import android.app.Application
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.hw14app.model.Word
import com.example.hw14app.repository.DictionaryRepository
import java.io.IOException

class MainViewModel(app: Application) : AndroidViewModel(app) {

    var dictionary = listOf<Word>()
    var wordCountLiveData = MutableLiveData<Int>(0)
    private var player: MediaPlayer? = null
    private var recorder: MediaRecorder? = null

    init {
        DictionaryRepository.initDB(app.applicationContext)
        dictionary = DictionaryRepository.getAllWords()!!
        wordCountLiveData.value = DictionaryRepository.getWordsCount2()!!

    }

    fun addWord(word: Word) {
        DictionaryRepository.insertWord(word)
    }

    fun deleteWord(word: Word) {
        DictionaryRepository.deleteWord(word)
    }

    fun updateWord(word: Word) {
        DictionaryRepository.updateWord(word)
    }

    fun getWord(wordId: Int): Word? {
        return DictionaryRepository.getWordWithID(wordId)
    }

    fun getWordWithPersian(persian: String): Word? {
        return DictionaryRepository.getWordWithPersian(persian)
    }

    fun getWordWithEnglish(english: String): Word? {
        return DictionaryRepository.getWordWithEnglish(english)
    }

    fun startPlaying(address: String) {
        player = MediaPlayer().apply {
            try {
                setDataSource(address)
                prepare()
                start()
            } catch (e: IOException) {
//                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    fun startRecording(address: String) {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(address)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
            }

            start()
        }
    }

    fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    fun updateViews() {
        wordCountLiveData.value = DictionaryRepository.getWordsCount2()!!
        dictionary = DictionaryRepository.getAllWords()!!
    }

}