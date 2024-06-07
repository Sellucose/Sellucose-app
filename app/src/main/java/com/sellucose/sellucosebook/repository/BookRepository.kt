//// File: BookRepository.kt
//package com.sellucose.sellucosebook.repository
//
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.util.Log
//import com.sellucose.sellucosebook.data.EpubBook
//import nl.siegmann.epublib.epub.EpubReader
//
//class BookRepository {
//    fun getBook(uri: Uri): EpubBook {
//        val context = MyApplication.getContext()
//        val inputStream = context.contentResolver.openInputStream(uri)
//        Log.d("BookRepository", "InputStream opened for $uri")
//        val book = EpubReader().readEpub(inputStream)
//        Log.d("BookRepository", "Book read: ${book.title}")
//        val cover = BitmapFactory.decodeStream(book.coverImage.inputStream)
//        Log.d("BookRepository", "Cover image decoded")
//        val authorName = book.metadata.authors.firstOrNull()?.let { "${it.firstname} ${it.lastname}" } ?: "Unknown"
//        Log.d("BookRepository", "Author: $authorName")
//        return EpubBook(book.title, authorName, cover, uri.path) // Kirim path file .epub
//    }
//}


//// File: BookRepository.kt
//package com.sellucose.sellucosebook.repository
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.util.Log
//import com.sellucose.sellucosebook.data.EpubBook
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import org.readium.r2.shared.fetcher.Fetcher
//import org.readium.r2.shared.fetcher.Resource
//import org.readium.r2.shared.util.mediatype.MediaType
//import org.readium.r2.streamer.Streamer
//import java.io.File
//import org.readium.r2.shared.publication.Publication
//import org.readium.r2.shared.publication.asset.FileAsset
//import java.io.ByteArrayInputStream
//
//class BookRepository {
//    suspend fun getBook(context: Context, uri: Uri): EpubBook? = withContext(Dispatchers.IO) {
//        val inputStream = context.contentResolver.openInputStream(uri)
//        Log.d("BookRepository", "InputStream opened for $uri")
//        val tempFile = File.createTempFile("tempEpub", ".epub", context.cacheDir)
//        inputStream?.copyTo(tempFile.outputStream())
//        val streamer = Streamer(context)
//        val asset = FileAsset(tempFile)
//        val result = streamer.open(asset = asset, allowUserInteraction = false, sender = this)
//        tempFile.delete()
//
//        if (result.isFailure) {
//            Log.e("BookRepository", "Failed to open publication: ${result.exceptionOrNull()}")
//            return@withContext null
//        }
//
//        val publication = result.getOrNull()
//        Log.d("BookRepository", "Book read: ${publication?.metadata?.title}")
//        val cover = publication?.let { getCoverImage(it) }
//        Log.d("BookRepository", "Cover image decoded")
//        val authorName = publication?.metadata?.authors?.joinToString(", ") { it.name }
//        Log.d("BookRepository", "Author: $authorName")
//
//        // Kirim instance Publication ke konstruktor EpubBook
//        EpubBook(publication?.metadata?.title ?: "", authorName ?: "", cover, uri.toString(), publication)
//    }
//
//    private suspend fun getCoverImage(publication: Publication): Bitmap? {
//        val coverLink = publication.readingOrder.firstOrNull { it.type == "image/jpeg" || it.type == "image/png" || it.type == "image/gif" || it.type == "image/webp" }
//        val coverResource = coverLink?.let { publication.get(it) }
//        val coverByteArray = coverResource?.read()
//        val coverStream = coverByteArray?.let { ByteArrayInputStream(it.getOrNull()) }
//        return coverStream?.let { BitmapFactory.decodeStream(it) }
//    }
//}

// File: BookRepository.kt
//package com.sellucose.sellucosebook.repository
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.util.Log
//import com.sellucose.sellucosebook.data.EpubBook
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import nl.siegmann.epublib.epub.EpubReader
//import org.readium.r2.shared.publication.Publication
//import org.readium.r2.shared.publication.asset.FileAsset
//import org.readium.r2.shared.util.mediatype.MediaType
//import org.readium.r2.streamer.Streamer
//import java.io.File
//
//class BookRepository {
//    suspend fun getBook(context: Context, uri: Uri): EpubBook? = withContext(Dispatchers.IO) {
//        val inputStream = context.contentResolver.openInputStream(uri)
//        Log.d("BookRepository", "InputStream opened for $uri")
//        val tempFile = File.createTempFile("tempEpub", ".epub", context.cacheDir)
//        inputStream?.copyTo(tempFile.outputStream())
//        val streamer = Streamer(context)
//        val asset = FileAsset(tempFile)
//        val result = streamer.open(asset = asset, allowUserInteraction = false, sender = this)
//        tempFile.delete()
//
//        if (result.isFailure) {
//            Log.e("BookRepository", "Failed to open publication: ${result.exceptionOrNull()}")
//            return@withContext null
//        }
//
//        val publication = result.getOrNull()
//        Log.d("BookRepository", "Book read: ${publication?.metadata?.title}")
//        val authorName = publication?.metadata?.authors?.joinToString(", ") { it.name }
//        Log.d("BookRepository", "Author: $authorName")
//
//        // Use Epublib to get the cover image
//        val epublibBook = EpubReader().readEpub(inputStream)
//        val cover = epublibBook.coverImage?.inputStream?.let { BitmapFactory.decodeStream(it) }
//        Log.d("BookRepository", "Cover image decoded")
//
//        // Return the EpubBook with the cover image from Epublib and other data from Readium
//        EpubBook(publication?.metadata?.title ?: "", authorName ?: "", cover, uri.toString(), publication)
//    }
//}

// File: BookRepository.kt
//package com.sellucose.sellucosebook.repository
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.util.Log
//import com.sellucose.sellucosebook.data.EpubBook
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//import nl.siegmann.epublib.epub.EpubReader
//import org.readium.r2.shared.publication.Publication
//import org.readium.r2.shared.publication.asset.FileAsset
//import org.readium.r2.shared.util.mediatype.MediaType
//import org.readium.r2.streamer.Streamer
//import java.io.File
//
//class BookRepository {
//    suspend fun getBook(context: Context, uri: Uri): EpubBook? = withContext(Dispatchers.IO) {
//        val inputStream = context.contentResolver.openInputStream(uri)
//        Log.d("BookRepository", "InputStream opened for $uri")
//        val tempFile = File.createTempFile("tempEpub", ".epub", context.cacheDir)
//        inputStream?.copyTo(tempFile.outputStream())
//        val streamer = Streamer(context)
//        val asset = FileAsset(tempFile)
//        val result = streamer.open(asset = asset, allowUserInteraction = false, sender = this)
//        tempFile.delete()
//
//        if (result.isFailure) {
//            Log.e("BookRepository", "Failed to open publication: ${result.exceptionOrNull()}")
//            return@withContext null
//        }
//
//        val publication = result.getOrNull()
//        Log.d("BookRepository", "Book read: ${publication?.metadata?.title}")
//        val authorName = publication?.metadata?.authors?.joinToString(", ") { it.name }
//        Log.d("BookRepository", "Author: $authorName")
//
//        // Use Epublib to get the cover image
//        val epublibBook = EpubReader().readEpub(inputStream)
//        val cover = epublibBook.coverImage?.inputStream?.let { BitmapFactory.decodeStream(it) }
//        Log.d("BookRepository", "Cover image decoded: ${cover != null}")
//
//        // Return the EpubBook with the cover image from Epublib and other data from Readium
//        EpubBook(publication?.metadata?.title ?: "", authorName ?: "", cover, uri.toString(), publication)
//    }
//}

package com.sellucose.sellucosebook.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import com.sellucose.sellucosebook.data.EpubBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nl.siegmann.epublib.epub.EpubReader
import org.readium.r2.shared.publication.Publication
import org.readium.r2.shared.publication.asset.FileAsset
import org.readium.r2.shared.util.mediatype.MediaType
import org.readium.r2.streamer.Streamer
import java.io.File
import java.io.InputStream

class BookRepository {
    suspend fun getBook(context: Context, uri: Uri): EpubBook? = withContext(Dispatchers.IO) {
        var inputStream: InputStream? = null
        try {
            inputStream = context.contentResolver.openInputStream(uri)
            if (inputStream == null) {
                Log.e("BookRepository", "Failed to open InputStream for $uri")
                return@withContext null
            }
            Log.d("BookRepository", "InputStream opened for $uri")

            val tempFile = File.createTempFile("tempEpub", ".epub", context.cacheDir)
            inputStream.copyTo(tempFile.outputStream())
            val streamer = Streamer(context)
            val asset = FileAsset(tempFile)
            val result = streamer.open(asset = asset, allowUserInteraction = false, sender = this)
            tempFile.delete()

            if (result.isFailure) {
                Log.e("BookRepository", "Failed to open publication: ${result.exceptionOrNull()}")
                return@withContext null
            }

            val publication = result.getOrNull()
            Log.d("BookRepository", "Book read: ${publication?.metadata?.title}")
            val authorName = publication?.metadata?.authors?.joinToString(", ") { it.name }
            Log.d("BookRepository", "Author: $authorName")

            // Get cover image from Readium
            val coverLink = publication?.linkWithRel("cover")
            var coverBitmap: Bitmap? = null
            if (coverLink != null) {
                val baseUrl = publication.baseUrl
                val coverUri = if (baseUrl != null) {
                    Uri.parse(baseUrl.toString()).buildUpon().appendEncodedPath(coverLink.href).build().toString()
                } else {
                    null
                }
                coverBitmap = coverUri?.let { uriString ->
                    var coverStream: InputStream? = null
                    try {
                        coverStream = context.contentResolver.openInputStream(Uri.parse(uriString))
                        BitmapFactory.decodeStream(coverStream)
                    } catch (e: Exception) {
                        Log.e("BookRepository", "Error decoding cover image", e)
                        null
                    } finally {
                        coverStream?.close()
                    }
                }
            }

            // If cover image is not found using Readium, try using Epublib
            if (coverBitmap == null) {
                Log.d("BookRepository", "Trying to get cover image using Epublib")
                var epubInputStream: InputStream? = null
                try {
                    epubInputStream = context.contentResolver.openInputStream(uri)
                    val epublibBook = EpubReader().readEpub(epubInputStream)
                    coverBitmap = epublibBook.coverImage?.inputStream?.let { BitmapFactory.decodeStream(it) }
                } catch (e: Exception) {
                    Log.e("BookRepository", "Error decoding cover image using Epublib", e)
                } finally {
                    epubInputStream?.close()
                }
            }

            Log.d("BookRepository", "Cover image decoded: ${coverBitmap != null}")

            EpubBook(publication?.metadata?.title ?: "", authorName ?: "", coverBitmap, uri.toString(), publication)
        } finally {
            inputStream?.close()
        }
    }
}

