//package com.sellucose.sellucosebook.ui.myBooks
//
//import android.content.Intent
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.sellucose.sellucosebook.data.EpubBook
//import com.sellucose.sellucosebook.databinding.ItemMybookBinding
//
//class MyBooksAdapter : ListAdapter<EpubBook, MyBooksAdapter.BookViewHolder>(EpubBookDiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
//        val binding = ItemMybookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return BookViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
//        val book = getItem(position)
//        holder.bind(book)
//    }
//
//    // File: MyBooksAdapter.kt
//    class BookViewHolder(private val binding: ItemMybookBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(book: EpubBook) {
//            binding.bookTitle.text = book.title
//            binding.author.text = book.author
//
//            // Gunakan Glide untuk memuat gambar cover
//            Glide.with(binding.root)
//                .load(book.cover)
//                .into(binding.bookImage)
//
//            // Ketika item ditekan, mulai BookReaderActivity dan kirim URI file .epub sebagai intent extra
//            itemView.setOnClickListener {
//                Log.d("MyBooksAdapter", "Item clicked: ${book.title}")
//                val intent = Intent(it.context, BookReaderActivity::class.java).apply {
//                    putExtra(BookReaderActivity.EXTRA_EPUB_PATH, book.filePath)
//                }
//                it.context.startActivity(intent)
//            }
//        }
//    }
//}
//
//class EpubBookDiffCallback : DiffUtil.ItemCallback<EpubBook>() {
//    override fun areItemsTheSame(oldItem: EpubBook, newItem: EpubBook): Boolean {
//        // Replace this with your own logic
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(oldItem: EpubBook, newItem: EpubBook): Boolean {
//        // Replace this with your own logic
//        return oldItem == newItem
//    }
//}

package com.sellucose.sellucosebook.ui.myBooks

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sellucose.sellucosebook.R
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

            // Use Glide to load cover image
            Glide.with(binding.root)
                .load(book.cover)
                .placeholder(R.drawable.placeholder_image) // Optional placeholder image
                .error(R.drawable.error_image) // Optional error image
                .into(binding.bookImage)

            itemView.setOnClickListener {
                Log.d("MyBooksAdapter", "Item clicked: ${book.title}")
                val intent = Intent(it.context, BookReaderActivity::class.java).apply {
                    putExtra(BookReaderActivity.EXTRA_EPUB_PATH, book.filePath)
                }
                it.context.startActivity(intent)
            }
        }
    }
}

class EpubBookDiffCallback : DiffUtil.ItemCallback<EpubBook>() {
    override fun areItemsTheSame(oldItem: EpubBook, newItem: EpubBook): Boolean {
        return oldItem.filePath == newItem.filePath
    }

    override fun areContentsTheSame(oldItem: EpubBook, newItem: EpubBook): Boolean {
        return oldItem == newItem
    }
}

