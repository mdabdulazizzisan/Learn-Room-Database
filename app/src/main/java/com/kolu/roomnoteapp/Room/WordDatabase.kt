package com.kolu.roomnoteapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kolu.roomnoteapp.Model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1)
abstract class WordDatabase: RoomDatabase() {

    abstract fun getWordDao(): WordDao

    companion object{
        private var INSTANCE: WordDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): WordDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    WordDatabase::class.java,
                    "word_table")
                    .addCallback(WordDbCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    class WordDbCallback(val scope: CoroutineScope): RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let {
                scope.launch {
                    val dao = it.getWordDao()
                    dao.insert(Word("Sample Note 1", "Sample Note Description 1"))
                    dao.insert(Word("Sample Note 2", "Sample Note Description 2"))
                    dao.insert(Word("Sample Note 3", "Description of Sample Note 3"))
                }
            }
        }
    }
}