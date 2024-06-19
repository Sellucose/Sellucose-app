package com.dicoding.picodiploma.loginwithanimation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.target.Target
import com.dicoding.picodiploma.loginwithanimation.GlideApp
import com.dicoding.picodiploma.loginwithanimation.data.remote.book.BooksItem
import com.dicoding.picodiploma.loginwithanimation.databinding.ItemSavedBookBinding

class GetSavedBookAdapter : ListAdapter<BooksItem, GetSavedBookAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSavedBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = getItem(position)
        holder.bind(book)
    }

    class ViewHolder(private val binding: ItemSavedBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BooksItem?) {
            binding.bookTitle.text = book?.book?.bookTitle
            binding.author.text = book?.book?.bookAuthor
            GlideApp.with(binding.root)
                .load(book?.book?.imageURLM)
                .override(Target.SIZE_ORIGINAL)
                .into(binding.bookImage)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BooksItem>() {
        override fun areItemsTheSame(oldItem: BooksItem, newItem: BooksItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BooksItem, newItem: BooksItem): Boolean {
            return oldItem == newItem
        }
    }
}