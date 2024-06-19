package com.sellucose.sellucosebook.ui.customview

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import com.sellucose.sellucosebook.R

class CustomNameInputLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    fun validateName(): Boolean {
        val name = editText?.text.toString()
        return if (name.isEmpty()) {
            error = context.getString(R.string.name_error)
            false
        } else {
            error = null
            true
        }
    }
}