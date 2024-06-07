package com.sellucose.sellucosebook.data

import android.graphics.Bitmap
import org.readium.r2.shared.publication.Publication

// Modifikasi kelas EpubBook untuk menambahkan properti publication
data class EpubBook(
    val title: String,
    val author: String,
    val cover: Bitmap?,
    val filePath: String?, // Tambahkan field ini
    val publication: Publication? // Tambahkan field ini
)