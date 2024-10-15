package com.kolu.roomnoteapp.Repository

import androidx.annotation.WorkerThread
import com.kolu.roomnoteapp.Model.Word
import com.kolu.roomnoteapp.Room.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    val allNotes: Flow<List<Word>> = wordDao.getAllNotes()

    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }

    @WorkerThread
    suspend fun update(word: Word){
        wordDao.update(word)
    }

    @WorkerThread
    suspend fun delete(word: Word){
        wordDao.delete(word)
    }

    @WorkerThread
    suspend fun deleteAll(){
        wordDao.deleteAllWords()
    }
}