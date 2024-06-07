// File: BookReaderActivity.kt
//package com.sellucose.sellucosebook.ui.myBooks
//
//import android.annotation.SuppressLint
//import android.net.Uri
//import android.os.Bundle
//import android.util.Log
//import android.webkit.WebResourceError
//import android.webkit.WebResourceRequest
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import androidx.appcompat.app.AppCompatActivity
//import com.sellucose.sellucosebook.R
//import com.sellucose.sellucosebook.repository.BookRepository
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import org.readium.r2.streamer.server.Server
//
//class BookReaderActivity : AppCompatActivity() {
//
//    companion object {
//        const val EXTRA_EPUB_PATH = "extra_epub_path"
//    }
//
//    private lateinit var webView: WebView
//    private lateinit var server: Server
//
//    @SuppressLint("SetJavaScriptEnabled")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_book_reader)
//
//        webView = findViewById(R.id.webView)
//        webView.settings.javaScriptEnabled = true
//        webView.settings.domStorageEnabled = true
//        webView.settings.allowFileAccess = true
//        webView.settings.allowContentAccess = true
//
//        webView.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                Log.d("WebView", "Page finished loading: $url")
//            }
//
//            override fun onReceivedError(
//                view: WebView?,
//                request: WebResourceRequest?,
//                error: WebResourceError?
//            ) {
//                super.onReceivedError(view, request, error)
//                Log.e("WebView", "Error loading page: ${error?.description}")
//            }
//        }
//
//        val epubUri = intent.getStringExtra(EXTRA_EPUB_PATH)
//        if (epubUri != null) {
//            CoroutineScope(Dispatchers.Main).launch {
//                val bookRepository = BookRepository()
//                val book = bookRepository.getBook(this@BookReaderActivity, Uri.parse(epubUri))
//
//                if (book != null) {
//                    server = Server(8080, this@BookReaderActivity)
//                    server.start()
//                    Log.d("BookReaderActivity", "Server started at http://127.0.0.1:8080")
//                    val publication = book.publication
//                    if (publication != null) {
//                        val baseUrl = server.addPublication(publication)
//                        if (baseUrl != null) {
//                            for (link in publication.readingOrder) {
//                                val url = baseUrl.toString() + link.href
//                                Log.d("BookReaderActivity", "Loading URL: $url")
//                                webView.loadUrl(url)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if (::server.isInitialized) {
//            server.stop()
//        }
//    }
//}

package com.sellucose.sellucosebook.ui.myBooks

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.sellucose.sellucosebook.R
import com.sellucose.sellucosebook.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.readium.r2.streamer.server.Server

class BookReaderActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_EPUB_PATH = "extra_epub_path"
    }

    private lateinit var webView: WebView
    private lateinit var server: Server
    private val client = OkHttpClient()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_reader)

        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.allowFileAccess = true
        webView.settings.allowContentAccess = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.d("WebView", "Page finished loading: $url")
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                Log.e("WebView", "Error loading page: ${error?.description}")
            }
        }

        val epubUri = intent.getStringExtra(EXTRA_EPUB_PATH)
        if (epubUri != null) {
            CoroutineScope(Dispatchers.Main).launch {
                val bookRepository = BookRepository()
                val book = bookRepository.getBook(this@BookReaderActivity, Uri.parse(epubUri))

                if (book != null) {
                    server = Server(8080, this@BookReaderActivity)
                    server.start()
                    Log.d("BookReaderActivity", "Server started at http://127.0.0.1:8080")
                    val publication = book.publication
                    if (publication != null) {
                        val baseUrl = server.addPublication(publication)
                        if (baseUrl != null) {
                            val htmlContent = StringBuilder()
                            htmlContent.append("<html><body>")

                            for (link in publication.readingOrder) {
                                val url = baseUrl.toString() + link.href
                                val sectionHtml = loadHtmlFromUrl(url)
                                htmlContent.append(sectionHtml)
                                Log.d("BookReaderActivity", "Appending content from: $url")
                            }

                            htmlContent.append("</body></html>")
                            webView.loadDataWithBaseURL(null, htmlContent.toString(), "text/html", "UTF-8", null)
                        }
                    }
                }
            }
        }
    }

    private suspend fun loadHtmlFromUrl(url: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    response.body?.string() ?: ""
                } else {
                    Log.e("BookReaderActivity", "Failed to load URL: $url")
                    ""
                }
            } catch (e: Exception) {
                Log.e("BookReaderActivity", "Error loading URL: $url", e)
                ""
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::server.isInitialized) {
            server.stop()
        }
    }
}

