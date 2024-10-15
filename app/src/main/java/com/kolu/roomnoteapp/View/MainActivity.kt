package com.kolu.roomnoteapp.View

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kolu.roomnoteapp.Adapters.WordAdapter
import com.kolu.roomnoteapp.Model.Word
import com.kolu.roomnoteapp.R
import com.kolu.roomnoteapp.ViewModel.WordViewModel
import com.kolu.roomnoteapp.ViewModel.WordViewModelFactory
import com.kolu.roomnoteapp.WordApplication
import kotlinx.coroutines.launch
import kotlin.jvm.Throws

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etDescription: EditText
    lateinit var btnAdd: Button
    lateinit var viewModel: WordViewModel
    lateinit var rvWordList: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = (application as WordApplication).repository
        val adapter = WordAdapter()

        rvWordList = findViewById(R.id.recyclerViewWordList)
        etName = findViewById(R.id.etName)
        etDescription = findViewById(R.id.etDescription)
        btnAdd = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {
            if (etName.text.toString() == "" || etDescription.text.toString() == ""){
                Toast.makeText(this, "Title or Description can't be empty", Toast.LENGTH_LONG).show()
            }
            else{
                viewModel.insert(Word(etName.text.toString(), etDescription.text.toString()))
            }
        }

        rvWordList.layoutManager = LinearLayoutManager(this)
        rvWordList.adapter = adapter
        val wordViewModelFactory = WordViewModelFactory(repository)
        viewModel = ViewModelProvider(this, wordViewModelFactory)[WordViewModel::class]

        viewModel.allWords.observe(this, Observer { words ->
            adapter.setWord(words)
        })
    }
}