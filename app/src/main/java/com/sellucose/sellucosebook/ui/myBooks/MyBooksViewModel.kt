package com.sellucose.sellucosebook.ui.myBooks

import androidx.lifecycle.ViewModel
import com.sellucose.sellucosebook.data.EpubBook

class MyBooksViewModel : ViewModel() {
    val books = mutableListOf<EpubBook>()
}