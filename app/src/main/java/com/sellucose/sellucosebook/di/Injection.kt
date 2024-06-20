package com.dicoding.picodiploma.loginwithanimation.di

import android.content.Context
import com.sellucose.sellucosebook.data.repository.AuthRepository
import com.sellucose.sellucosebook.data.pref.UserPreference
import com.sellucose.sellucosebook.data.pref.dataStore
import com.sellucose.sellucosebook.data.remote.auth.AuthConfig
import com.sellucose.sellucosebook.data.remote.auth.AuthService
import com.sellucose.sellucosebook.data.remote.book.SavedBookConfig
import com.sellucose.sellucosebook.data.remote.book.SavedBookService
import com.sellucose.sellucosebook.data.repository.SavedBookRepository

object Injection {

    fun provideAuthRepository(context: Context): AuthRepository {
        val authService = provideAuthService()
        val userPreference = UserPreference.getInstance(context.dataStore)
        return AuthRepository.getInstance(authService, userPreference)
    }

    fun provideSavedBookRepository(context: Context): SavedBookRepository {
        val bookService = provideBookService(context)
        val userPreference = UserPreference.getInstance(context.dataStore)
        return SavedBookRepository.getInstance(bookService, userPreference)
    }

    private fun provideAuthService(): AuthService {
        return AuthConfig.getApiService()
    }

    private fun provideBookService(context: Context): SavedBookService {
        return SavedBookConfig.getApiService(context)
    }
}