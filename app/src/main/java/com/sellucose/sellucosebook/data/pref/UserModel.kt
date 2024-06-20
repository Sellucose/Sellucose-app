package com.sellucose.sellucosebook.data.pref

data class  UserModel(
    val keyword: String,
    val token: String = "",
    val isLogin: Boolean = false
)