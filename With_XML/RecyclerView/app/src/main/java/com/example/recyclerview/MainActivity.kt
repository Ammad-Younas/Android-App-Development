package com.example.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Recycler View"


        recyclerView = findViewById(R.id.recyclerView)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val contact = ArrayList<ContactModel>()
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))
        contact.add(ContactModel(R.drawable.avatar, "John Doe", "abc@example.com", "12:34PM"))


        val recyclerAdapter = RecyclerContactAdapter(this, contact)
        recyclerView.adapter = recyclerAdapter
    }
}