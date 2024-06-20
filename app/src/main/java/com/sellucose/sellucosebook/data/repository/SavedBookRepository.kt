package com.sellucose.sellucosebook.data.repository

import com.sellucose.sellucosebook.data.remote.book.SavedBookService
import com.sellucose.sellucosebook.data.pref.UserPreference
import kotlinx.coroutines.flow.first

class SavedBookRepository(
    private val savedBookService: SavedBookService,
    private val userPreference: UserPreference
) {

    suspend fun getBooks(lastCreatedAt: String? = null) =
        savedBookService.getBooks("Bearer ${userPreference.getSession().first().token}", lastCreatedAt)

    companion object {
        @Volatile
        private var instance: SavedBookRepository? = null

        fun getInstance(savedBookService: SavedBookService, userPreference: UserPreference): SavedBookRepository =
            instance ?: synchronized(this) {
                instance ?: SavedBookRepository(savedBookService, userPreference).also { instance = it }
            }
    }
}