package com.kolu.roomnoteapp.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kolu.roomnoteapp.Model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert
    suspend fun insert(word: Word)

    @Delete
    suspend fun delete(word: Word)

    @Update
    suspend fun update(word: Word)

    @Query("select * from word_table order by id desc")
    fun getAllNotes(): Flow<List<Word>>

    @Query("delete from word_table")
    suspend fun deleteAllWords()
}