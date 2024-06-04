package com.sellucose.sellucosebook.repository

import android.graphics.BitmapFactory
import android.net.Uri
import com.sellucose.sellucosebook.data.EpubBook
import nl.siegmann.epublib.epub.EpubReader

class BookRepository {
    fun getBook(uri: Uri): EpubBook {
        val context = MyApplication.getContext()
        val inputStream = context.contentResolver.openInputStream(uri)
        val book = EpubReader().readEpub(inputStream)
        val cover = BitmapFactory.decodeStream(book.coverImage.inputStream)
        val authorName = book.metadata.authors.firstOrNull()?.let { "${it.firstname} ${it.lastname}" } ?: "Unknown"
        return EpubBook(book.title, authorName, cover)
    }
}