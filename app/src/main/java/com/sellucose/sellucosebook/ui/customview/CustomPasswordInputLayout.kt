package com.sellucose.sellucosebook.ui.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.sellucose.sellucosebook.R

class CustomPasswordInputLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    private var errorMessage: String? = null

    init {
        editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validatePassword()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }
        })
    }

    fun validatePassword(): Boolean {
        val password = editText?.text.toString()
        val isPasswordValid = password.length >= 8
        errorMessage = if (isPasswordValid) {
            null
        } else {
            context.getString(R.string.password_error)
        }
        error = errorMessage
        return isPasswordValid
    }
}