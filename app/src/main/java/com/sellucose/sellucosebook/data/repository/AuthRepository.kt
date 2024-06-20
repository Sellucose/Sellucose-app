package com.sellucose.sellucosebook.data.repository

import com.sellucose.sellucosebook.data.pref.UserModel
import com.sellucose.sellucosebook.data.pref.UserPreference
import com.sellucose.sellucosebook.data.remote.auth.AuthService
import com.sellucose.sellucosebook.data.remote.body.LoginBody
import com.sellucose.sellucosebook.data.remote.body.RegisterBody
import kotlinx.coroutines.flow.Flow

class AuthRepository(
    private val authService: AuthService,
    private val userPreference: UserPreference
) {

    suspend fun login(keyword: String, password: String) =
        authService.login(LoginBody(keyword, password))

    suspend fun register(email: String, username: String, password: String, passwordConfirmation: String) =
        authService.register(RegisterBody(email, username, password, passwordConfirmation))

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    companion object {
        @Volatile
        private var instance: AuthRepository? = null

        fun getInstance(authService: AuthService, userPreference: UserPreference): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(authService, userPreference).also { instance = it }
            }
    }
}