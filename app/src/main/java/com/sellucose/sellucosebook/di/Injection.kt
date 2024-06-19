package com.dicoding.picodiploma.loginwithanimation.di

import android.content.Context
import com.dicoding.picodiploma.loginwithanimation.data.repository.AuthRepository
import com.dicoding.picodiploma.loginwithanimation.data.repository.BookRepository
import com.dicoding.picodiploma.loginwithanimation.data.pref.UserPreference
import com.dicoding.picodiploma.loginwithanimation.data.pref.dataStore
import com.dicoding.picodiploma.loginwithanimation.data.remote.auth.AuthConfig
import com.dicoding.picodiploma.loginwithanimation.data.remote.auth.AuthService
import com.dicoding.picodiploma.loginwithanimation.data.remote.book.BookConfig
import com.dicoding.picodiploma.loginwithanimation.data.remote.book.BookService

object Injection {

    fun provideAuthRepository(context: Context): AuthRepository {
        val authService = provideAuthService()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return AuthRepository.getInstance(authService, userPreference)
    }

    fun provideBookRepository(context: Context): BookRepository {
        val bookService = provideBookService(context)
        val userPreference = UserPreference.getInstance(context.dataStore)
        return BookRepository.getInstance(bookService, userPreference)
    }

    private fun provideAuthService(): AuthService {
        return AuthConfig.getApiService()
    }

    private fun provideBookService(context: Context): BookService {
        return BookConfig.getApiService(context)
    }
}