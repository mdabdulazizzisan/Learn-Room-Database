package com.kolu.roomnoteapp

import android.app.Application
import com.kolu.roomnoteapp.Repository.WordRepository
import com.kolu.roomnoteapp.Room.WordDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { WordDatabase.getDatabase(this, applicationScope) }
    val repository by lazy {WordRepository(database.getWordDao()) }
}