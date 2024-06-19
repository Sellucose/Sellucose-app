package com.dicoding.picodiploma.loginwithanimation.data.remote.auth

import com.google.gson.annotations.SerializedName

data class LoginRespone(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
