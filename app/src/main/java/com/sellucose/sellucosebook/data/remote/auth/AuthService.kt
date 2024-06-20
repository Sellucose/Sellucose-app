package com.sellucose.sellucosebook.data.remote.auth

import com.sellucose.sellucosebook.data.remote.body.LoginBody
import com.sellucose.sellucosebook.data.remote.body.RegisterBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    suspend fun login(@Body loginBody: LoginBody): Response<LoginRespone>

    @POST("register")
    suspend fun register(@Body registerBody: RegisterBody): Response<RegisterRespone>
}