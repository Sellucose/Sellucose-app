package com.sellucose.sellucosebook.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sellucose.sellucosebook.data.repository.AuthRepository
import kotlinx.coroutines.launch

class SignupViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun register(email: String, username: String, password: String, passwordConfirmation: String) {
        viewModelScope.launch {
            authRepository.register(email, username, password, passwordConfirmation)
        }
    }
}