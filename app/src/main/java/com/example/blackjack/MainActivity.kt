package com.example.blackjack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val game = Game()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val playerCard1: ImageView = findViewById(R.id.playerCard1)
        val playerCard2: ImageView = findViewById(R.id.playerCard2)
        val playerCard3: ImageView = findViewById(R.id.playerCard3)
        val playerCard4: ImageView = findViewById(R.id.playerCard4)


        runOnUiThread {
            game.dealCards()
            Log.d("MainActivity", "Player's hand: ${game.playerHand}")
            Log.d("MainActivity", "Card 1: ${game.playerHand[0]}")
            Log.d("MainActivity", "Card 2: ${game.playerHand[1]}")
            Log.d("MainActivity", "Image 1: ${getCardImage(game.playerHand[0])}")
            Log.d("MainActivity", "Image 2: ${getCardImage(game.playerHand[1])}")

            playerCard1.setImageResource(getCardImage(game.playerHand[0]))
            playerCard2.setImageResource(getCardImage(game.playerHand[1]))

            game.determineWinner(game.playerHand, game.dealerHand)
        }

        val standButton = findViewById<Button>(R.id.standButton)
        val hitButton = findViewById<Button>(R.id.hitButton)

        standButton.setOnClickListener {
            val winnerMessage = game.determineWinner(game.playerHand, game.dealerHand)
            val intent = Intent(this, WinnerActivity::class.java)
            intent.putExtra("EXTRA_WINNER", winnerMessage)
            startActivity(intent)
        }

        var hitButtonCount = 0

        hitButton.setOnClickListener {
            hitButtonCount++
            val newCard = drawCard()
            game.playerHand.add(newCard)

            val playerCardImages = listOf(playerCard1, playerCard2, playerCard3, playerCard4)
            for (i in game.playerHand.indices) {
                playerCardImages[i].setImageResource(getCardImage(game.playerHand[i]))
            }
            if (hitButtonCount == 2) {
                val winnerMessage = game.determineWinner(game.playerHand, game.dealerHand)
                val intent = Intent(this, WinnerActivity::class.java)
                intent.putExtra("EXTRA_WINNER", winnerMessage)
                startActivity(intent)
            }
        }
    }

    fun getCardImage(card: Game.Card): Int {
        return when (card.imageName) {
            "hearts_2" -> R.drawable.hearts_2
            "hearts_3" -> R.drawable.hearts_3
            "hearts_4" -> R.drawable.hearts_4
            "hearts_5" -> R.drawable.hearts_5
            "hearts_6" -> R.drawable.hearts_6
            "hearts_7" -> R.drawable.hearts_7
            "hearts_8" -> R.drawable.hearts_8
            "hearts_9" -> R.drawable.hearts_9
            "hearts_10" -> R.drawable.hearts_10
            "clubs_2" -> R.drawable.clubs_2
            "clubs_3" -> R.drawable.clubs_3
            "clubs_4" -> R.drawable.clubs_4
            "clubs_5" -> R.drawable.clubs_5
            "clubs_6" -> R.drawable.clubs_6
            "clubs_7" -> R.drawable.clubs_7
            "clubs_8" -> R.drawable.clubs_8
            "clubs_9" -> R.drawable.clubs_9
            "clubs_10" -> R.drawable.clubs_10


            else -> {
                val resId = resources.getIdentifier(card.imageName, "drawable", packageName)
                Log.d("MainActivity", "Resource ID for ${card.imageName}: $resId")
                resId
            }
        }
    }

    fun drawCard(): Game.Card {
        val random = Random
        return game.deck.removeAt(random.nextInt(game.deck.size))
    }


}