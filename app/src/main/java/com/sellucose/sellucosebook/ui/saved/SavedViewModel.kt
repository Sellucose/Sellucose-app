package com.sellucose.sellucosebook.ui.saved

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sellucose.sellucosebook.data.pref.UserModel
import com.sellucose.sellucosebook.data.remote.book.BooksItem
import com.sellucose.sellucosebook.data.repository.AuthRepository
import com.sellucose.sellucosebook.data.repository.BookRepository
import com.sellucose.sellucosebook.data.repository.SavedBookRepository
import kotlinx.coroutines.launch

class SavedViewModel(
    private val authRepository: AuthRepository,
    private val savedBookRepository: SavedBookRepository
) : ViewModel() {
    private val _books = MutableLiveData<List<BooksItem?>>()
    val books: LiveData<List<BooksItem?>> get() = _books

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error


    fun getSession(): LiveData<UserModel> {
        return authRepository.getSession().asLiveData()
    }

    fun getBooks(lastCreatedAt: String? = null) {
        viewModelScope.launch {
            try {
                val response = savedBookRepository.getBooks(lastCreatedAt)
                if (response.isSuccessful) {
                    _books.value = response.body()?.data?.books ?: emptyList()
                } else {
                    _books.value = emptyList()
                    _error.value = "Failed to get books: ${response.message()}"
                }
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failed to get books", e)
                _error.value = "Failed to get books: ${e.message}"
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }
}