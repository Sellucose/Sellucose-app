package com.sellucose.sellucosebook.ui.rated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RatedViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is rated Fragment"
    }
    val text: LiveData<String> = _text
}