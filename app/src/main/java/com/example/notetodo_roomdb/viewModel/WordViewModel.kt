package com.example.notetodo_roomdb.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notetodo_roomdb.repository.WordRepository
import com.example.notetodo_roomdb.room.entitiy.Word


class WordViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val mRepository: WordRepository
    val allWords: LiveData<List<Word?>?>?

    fun insert(word: Word?) {
        mRepository.insert(word)
    }

    init {
        mRepository = WordRepository(application)
        allWords = mRepository.getAllWords()
    }
}
