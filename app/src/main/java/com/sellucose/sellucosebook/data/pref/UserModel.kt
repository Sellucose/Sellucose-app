package com.dicoding.picodiploma.loginwithanimation.data.pref

data class  UserModel(
    val keyword: String,
    val token: String = "",
    val isLogin: Boolean = false
)