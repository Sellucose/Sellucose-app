package com.sellucose.sellucosebook.ui.customview

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.sellucose.sellucosebook.R

class CustomEmailInputLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    fun checkEmailValidity(): Boolean {
        val email = editText?.text.toString()
        return if (!email.endsWith("@gmail.com")) {
            error = context.getString(R.string.email_error)
            false
        } else {
            error = null
            true
        }
    }
}