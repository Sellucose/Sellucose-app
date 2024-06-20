package com.sellucose.sellucosebook.ui.customview

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.sellucose.sellucosebook.R

class CustomKeywordInputLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    fun checkKeywordValidity(): Boolean {
        val keyword = editText?.text.toString()
        return if (keyword.contains("@")) {
            checkEmailValidity(keyword)
        } else {
            checkNameValidity(keyword)
        }
    }

    private fun checkEmailValidity(email: String): Boolean {
        return if (!email.endsWith("@yahoo.com")
            && !email.endsWith("@email.com")
            && !email.endsWith("@mail.com")) {
            error = context.getString(R.string.email_error)
            false
        } else {
            error = null
            true
        }
    }

    private fun checkNameValidity(name: String): Boolean {
        return if (name.isEmpty()) {
            error = context.getString(R.string.name_error)
            false
        } else {
            error = null
            true
        }
    }
}