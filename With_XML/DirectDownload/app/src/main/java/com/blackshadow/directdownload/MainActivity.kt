package com.blackshadow.directdownload

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var fileType: Spinner
    private lateinit var engineType: Spinner
    private lateinit var queryText: EditText
    private lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Direct Download"

        fileType = findViewById(R.id.fileType)
        engineType = findViewById(R.id.engineType)
        queryText = findViewById(R.id.queryTextView)
        searchButton = findViewById(R.id.button)

        searchButton.setOnClickListener {
            val filetype = fileType.selectedItem.toString().uppercase()
            val enginetype = engineType.selectedItem.toString().uppercase()
            val query = queryText.text.toString()

            if (query.isEmpty()) {
                Toast.makeText(this, "Write your query", Toast.LENGTH_SHORT).show()
            } else {
                val intentToResult = Intent(this, SearchResult::class.java).apply {
                    putExtra("FILE_TYPE", filetype)
                    putExtra("QUERY", query)
                }
                startActivity(intentToResult)
            }
        }
    }
}
