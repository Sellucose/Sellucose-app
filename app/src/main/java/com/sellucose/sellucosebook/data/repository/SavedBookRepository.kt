package com.sellucose.sellucosebook.data.repository

import com.sellucose.sellucosebook.data.remote.book.BookService
import com.sellucose.sellucosebook.data.pref.UserPreference
import kotlinx.coroutines.flow.first

class BookRepository(
    private val bookService: BookService,
    private val userPreference: UserPreference
) {

    suspend fun getBooks(lastCreatedAt: String? = null) =
        bookService.getBooks("Bearer ${userPreference.getSession().first().token}", lastCreatedAt)

    companion object {
        @Volatile
        private var instance: BookRepository? = null

        fun getInstance(bookService: BookService, userPreference: UserPreference): BookRepository =
            instance ?: synchronized(this) {
                instance ?: BookRepository(bookService, userPreference).also { instance = it }
            }
    }
}