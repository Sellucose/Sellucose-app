package com.dicoding.picodiploma.loginwithanimation.data.remote.auth

import com.google.gson.annotations.SerializedName

data class RegisterRespone(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("user_id")
	val userId: String? = null
)
