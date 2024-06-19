package com.dicoding.picodiploma.loginwithanimation.data.remote.auth

import com.dicoding.picodiploma.loginwithanimation.data.remote.body.LoginBody
import com.dicoding.picodiploma.loginwithanimation.data.remote.body.RegisterBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    suspend fun login(@Body loginBody: LoginBody): Response<LoginRespone>

    @POST("register")
    suspend fun register(@Body registerBody: RegisterBody): Response<RegisterRespone>
}