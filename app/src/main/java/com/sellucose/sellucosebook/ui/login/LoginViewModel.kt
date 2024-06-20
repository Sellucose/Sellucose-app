package com.sellucose.sellucosebook.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sellucose.sellucosebook.data.pref.UserModel
import com.sellucose.sellucosebook.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun login(keyword: String, password: String) {
        viewModelScope.launch {
            val response = authRepository.login(keyword, password)
            if (response.isSuccessful) {
                response.body()?.let { loginResponse ->
                    authRepository.saveSession(UserModel(keyword, loginResponse.token.toString(), true))
                }
            }
        }
    }
}