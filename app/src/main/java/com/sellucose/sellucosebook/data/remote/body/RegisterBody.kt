package com.sellucose.sellucosebook.data.remote.body

data class RegisterBody(

    var email: String,
    var username: String,
    var password: String,
    var password_confirmation: String
)
