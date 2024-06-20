package com.sellucose.sellucosebook.data.remote.book

import android.content.Context
import com.sellucose.sellucosebook.data.pref.UserPreference
import com.sellucose.sellucosebook.data.pref.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SavedBookConfig {
    fun getApiService(context: Context): SavedBookService {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val pref = UserPreference.getInstance(context.dataStore)
        val authInterceptor = Interceptor { chain ->
            val token = runBlocking { pref.getSession().first().token }
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(requestHeaders)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://main-app-prbyucn6xa-et.a.run.app/books/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(SavedBookService::class.java)
    }
}