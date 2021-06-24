package com.example.notetodo_roomdb.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notetodo_roomdb.room.dao.WordDao
import com.example.notetodo_roomdb.room.entitiy.Word
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao?

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(
            NUMBER_OF_THREADS
        )

        fun getDatabase(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            WordRoomDatabase::class.java, "word_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

    private val sRoomDatabaseCallback: Callback = object : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute {

                // Populate the database in the background.
                // If you want to start with more words, just add them.
                val dao = INSTANCE!!.wordDao()
                dao!!.deleteAll()
                var word = Word("Hello")
                dao.insert(word)
                word = Word("World")
                dao.insert(word)
            }
        }
    }

}
