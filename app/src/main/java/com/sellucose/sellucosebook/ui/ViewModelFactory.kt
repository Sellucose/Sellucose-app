package com.sellucose.sellucosebook.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.loginwithanimation.di.Injection
import com.sellucose.sellucosebook.data.repository.AuthRepository
import com.sellucose.sellucosebook.data.repository.SavedBookRepository
import com.sellucose.sellucosebook.ui.login.LoginViewModel
import com.sellucose.sellucosebook.ui.saved.SavedViewModel
import com.sellucose.sellucosebook.ui.signup.SignupViewModel

class ViewModelFactory private constructor(
    private val authRepository: AuthRepository,
    private val savedBookRepository: SavedBookRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SavedViewModel::class.java) -> {
                SavedViewModel(authRepository, savedBookRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(authRepository) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel(authRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(
                        Injection.provideAuthRepository(context),
                        Injection.provideSavedBookRepository(context)
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}