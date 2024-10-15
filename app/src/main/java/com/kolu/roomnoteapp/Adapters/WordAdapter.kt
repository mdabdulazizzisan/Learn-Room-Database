package com.kolu.roomnoteapp.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kolu.roomnoteapp.Model.Word
import com.kolu.roomnoteapp.R

class WordAdapter: RecyclerView.Adapter<WordAdapter.WordViewHolder>(){

    var wordList: List<Word> = ArrayList()

    class WordViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var wordName: TextView = itemView.findViewById(R.id.wordName)
        var wordDescription: TextView = itemView.findViewById(R.id.wordDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_item_view, parent, false)
        return WordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word: Word = wordList[position]
        holder.wordName.text = word.wordName
        holder.wordDescription.text = word.meaning
    }

    fun setWord(words: List<Word>){
        this.wordList = words
        notifyDataSetChanged()
    }
}