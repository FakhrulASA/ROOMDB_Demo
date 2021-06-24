package com.example.notetodo_roomdb.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.notetodo_roomdb.room.dao.WordDao
import com.example.notetodo_roomdb.room.database.WordRoomDatabase
import com.example.notetodo_roomdb.room.entitiy.Word


internal class WordRepository(application: Application?) {
    private var mWordDao: WordDao? = null
    private var mAllWords: LiveData<List<Word?>?>? = null

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    fun WordRepository(application: Application?) {
        val db = WordRoomDatabase.getDatabase(application!!)
        mWordDao = db!!.wordDao()
        mAllWords = mWordDao!!.getAlphabetizedWords()
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    fun getAllWords(): LiveData<List<Word?>?>? {
        return mAllWords
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    fun insert(word: Word?) {
        WordRoomDatabase.databaseWriteExecutor.execute { mWordDao!!.insert(word) }
    }

}
