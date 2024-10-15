package com.kolu.roomnoteapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    val wordName: String = "",
    val meaning: String = "",
    @PrimaryKey(autoGenerate = true) val id: Int = 0)