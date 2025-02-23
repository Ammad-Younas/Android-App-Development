package com.blackshadow.alertdialogue

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener(object : View.OnClickListener{
            override fun onClick(view: View) {
                showAlertDialogue(this@MainActivity, view)
            }
        })
    }

    fun showAlertDialogue(context : Context, view : View){
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Hello")
        dialog.setMessage("Do you really want to quit?")
        dialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface, which: Int) {
                Toast.makeText(context, "Yes button clicked!", Toast.LENGTH_SHORT).show()
            }
        })
        dialog.setNeutralButton("No", object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface, which: Int) {
                Toast.makeText(context, "No button clicked!", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        })
        dialog.show()
    }
}