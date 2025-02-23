package com.blackshadow.directdownload

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SearchResult : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_result)

        toolbar = findViewById(R.id.toolBarOfSearchResult)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Results from dorks"
        supportActionBar?.subtitle = "Loading results, please wait..."

        var webview = findViewById<WebView>(R.id.webView)
        webview.webViewClient = WebViewClient()
        webview.settings.javaScriptEnabled = true

        val fileType = intent.getStringExtra("FILE_TYPE")
        val query = intent.getStringExtra("QUERY")

        val fileTypeToDork = when (fileType) {
            "VIDEO" -> "+ (mkv|mp4|avi|mov|mpg|wmv|divx|mpeg|flv|vob) -inurl:(jsp|pl|php|html|aspx|htm|cf|shtml) intitle:index.of -inurl:(listen77|mp3raid|mp3toss|mp3drug|wallywashis|downloadmana)"
            "EBOOK" -> "+ (mobi|cbz|cbr|cbc|chm|epub|fb2|lit|lrf|odt|pdf|prc|pdb|pml|rb|rtf|tcr|doc|docx|azw|azw3) -inurl:(jsp|pl|php|html|aspx|htm|cf|shtml) intitle:index.of -inurl:(listen77|mp3raid|mp3toss|mp3drug|wallywashis|downloadmana)"
            "AUDIO" -> "+ (mp3|wav|ac3|ogg|flac|wma|m4a|aac|mod|alac|aiff|ape) -inurl:(jsp|pl|php|html|aspx|htm|cf|shtml) intitle:index.of -inurl:(listen77|mp3raid|mp3toss|mp3drug|wallywashis|downloadmana)"
            "SOFTWARE" -> "+ (exe|iso|dmg|tar|7z|bz2|gz|rar|zip|apk|deb|rpm) -inurl:(jsp|pl|php|html|aspx|htm|cf|shtml) intitle:index.of -inurl:(listen77|mp3raid|mp3toss|mp3drug|wallywashis|downloadmana)"
            "IMAGE" -> "+ (jpg|jpeg|png|bmp|gif|tif|tiff|psd|svg|webp) -inurl:(jsp|pl|php|html|aspx|htm|cf|shtml) intitle:index.of -inurl:(listen77|mp3raid|mp3toss|mp3drug|wallywashis|downloadmana)"
            "GENERAL" -> "- inurl:(jsp|pl|php|html|aspx|htm|cf|shtml) intitle:index.of -inurl:(listen77|mp3raid|mp3toss|mp3drug|wallywashis|downloadmana)"
            else -> "- inurl:(jsp|pl|php|html|aspx|htm|cf|shtml) intitle:index.of -inurl:(listen77|mp3raid|mp3toss|mp3drug|wallywashis|downloadmana)"
        }

        var finalURL = "https://www.google.com/search?q=${query?.replace(" ", "+")} $fileTypeToDork"
        webview.loadUrl(finalURL)
        supportActionBar?.subtitle = ""


        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webview.canGoBack()) {
                    webview.goBack()
                } else {
                    val intent = Intent(this@SearchResult, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }
            }
        })

    }
}