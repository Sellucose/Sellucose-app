package com.sellucose.sellucosebook.ui.myBooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sellucose.sellucosebook.data.EpubBook
import com.sellucose.sellucosebook.databinding.ItemMybookBinding

class MyBooksAdapter : ListAdapter<EpubBook, MyBooksAdapter.BookViewHolder>(EpubBookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemMybookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    class BookViewHolder(private val binding: ItemMybookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: EpubBook) {
            binding.bookTitle.text = book.title
            binding.author.text = book.author
            binding.bookImage.setImageBitmap(book.cover) // Gunakan gambar cover
        }
    }
}

class EpubBookDiffCallback : DiffUtil.ItemCallback<EpubBook>() {
    override fun areItemsTheSame(oldItem: EpubBook, newItem: EpubBook): Boolean {
        // Replace this with your own logic
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: EpubBook, newItem: EpubBook): Boolean {
        // Replace this with your own logic
        return oldItem == newItem
    }
}