package com.example.myapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw14app.R
import com.example.hw14app.model.Word

typealias WordClickHandler = (Word) -> Unit

class WordAdapter(var onWordClicked : WordClickHandler) :
    ListAdapter<Word, WordAdapter.ViewHolder>(WordDiffCallback) {


    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var btnWordDetail = view.findViewById<Button>(R.id.btnWordDetail)

        fun bind(word : Word, onWordClicked: WordClickHandler){
            btnWordDetail.text = word.id.toString()
            btnWordDetail.setOnClickListener {
                onWordClicked(word)
            }
        }
    }



    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): WordAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.word_item, viewGroup , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordAdapter.ViewHolder, position: Int) {
        val word = getItem(position)
        holder.bind(word, onWordClicked)
    }

}

object WordDiffCallback : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
        return oldItem.id == newItem.id
    }
}