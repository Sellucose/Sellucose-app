package com.sellucose.sellucosebook.data.remote.book

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SavedBookService {

    @GET("saved")
    suspend fun getBooks(
        @Header("Bearer") token: String,
        @Query("lastCreatedAt") lastCreatedAt: String? = null,
    ): Response<SavedBookResponse>

//    @GET("books/{id}")
//    suspend fun getBookById(@Path("id") id: Int): BookResponse
//
//    @POST("books")
//    suspend fun addBook(@Body bookRequest: BookRequest): BookResponse
//
//    @PUT("books/{id}")
//    suspend fun updateBook(@Path("id") id: Int, @Body bookRequest: BookRequest): BookResponse
//
//    @DELETE("books/{id}")
//    suspend fun deleteBook(@Path("id") id: Int): BookResponse
}