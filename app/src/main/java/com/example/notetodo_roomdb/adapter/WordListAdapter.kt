package com.example.notetodo_roomdb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notetodo_roomdb.R
import com.example.notetodo_roomdb.room.entitiy.Word


class WordListAdapter(diffCallback: DiffUtil.ItemCallback<Word?>) :
    ListAdapter<Word?, WordViewHolder?>(diffCallback) {
    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent!!)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current: Word? = getItem(position)
        if (current != null) {
            holder.bind(current.word)
        }
    }

    internal class WordDiff : DiffUtil.ItemCallback<Word?>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }
    }

}

class WordViewHolder private constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private val wordItemView: TextView = itemView.findViewById(R.id.textView)
    fun bind(text: String?) {
        wordItemView.text = text
    }

    companion object {
        fun create(parent: ViewGroup): WordViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
            return WordViewHolder(view)
        }
    }

}
