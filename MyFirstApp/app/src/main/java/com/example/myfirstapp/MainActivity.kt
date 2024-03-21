package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnClickMe = findViewById<Button>(R.id.mybutton)
        val tvMyTextView = findViewById<TextView>(R.id.textView)

        var timeClicked = 0
        btnClickMe.setOnClickListener {
            timeClicked = timeClicked + 1

            tvMyTextView.text = timeClicked.toString()
            Toast.makeText(this,"Hey I'm Rajashree!", Toast.LENGTH_SHORT).show()
        }

    }
}