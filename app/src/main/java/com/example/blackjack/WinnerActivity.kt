package com.example.blackjack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WinnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        val winnerTextView = findViewById<TextView>(R.id.winnerTextView)
        val winnerMessage = intent.getStringExtra("EXTRA_WINNER")
        winnerTextView.text = winnerMessage

        val backButton = findViewById<Button>(R.id.finishGameButton)

        backButton.setOnClickListener {
            val intent = Intent(this, StartActivity::class.java)
            startActivity(intent)
        }

        val dealButton = findViewById<Button>(R.id.dealButton)

        dealButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}