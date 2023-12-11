package com.example.blackjack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
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

        playerCard3.visibility = View.GONE
        playerCard4.visibility = View.GONE

        runOnUiThread {
            game.dealCards()

            playerCard1.setImageResource(getCardImage(game.playerHand[0]))
            playerCard2.setImageResource(getCardImage(game.playerHand[1]))
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
        val playerCardImages = listOf(playerCard1, playerCard2, playerCard3, playerCard4)

        hitButton.setOnClickListener {
            hitButtonCount++
            val newCard = drawCard()
            game.playerHand.add(newCard)

            playerCardImages[game.playerHand.size - 1].setImageResource(getCardImage(newCard))

            if (game.playerHand.size - 1 < playerCardImages.size) {
                playerCardImages[game.playerHand.size - 1].visibility = View.VISIBLE
            }

            if (hitButtonCount == 2) {
                val winnerMessage = game.determineWinner(game.playerHand, game.dealerHand)

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, WinnerActivity::class.java)
                    intent.putExtra("EXTRA_WINNER", winnerMessage)
                    startActivity(intent)
                }, 1000)
            }
        }
    }

    fun drawCard(): Game.Card {
        val random = Random
        val card = game.deck.removeAt(random.nextInt(game.deck.size))
        Log.d("MainActivity", "Drew card: $card")
        return card
    }

    fun getCardImage(card: Game.Card): Int {
        return when (card.imageName) {
            "HEARTS_2" -> R.drawable.hearts_2
            "HEARTS_3" -> R.drawable.hearts_3
            "HEARTS_4" -> R.drawable.hearts_4
            "HEARTS_5" -> R.drawable.hearts_5
            "HEARTS_6" -> R.drawable.hearts_6
            "HEARTS_7" -> R.drawable.hearts_7
            "HEARTS_8" -> R.drawable.hearts_8
            "HEARTS_9" -> R.drawable.hearts_9
            "HEARTS_10" -> R.drawable.hearts_10
            "CLUBS_2" -> R.drawable.clubs_2
            "CLUBS_3" -> R.drawable.clubs_3
            "CLUBS_4" -> R.drawable.clubs_4
            "CLUBS_5" -> R.drawable.clubs_5
            "CLUBS_6" -> R.drawable.clubs_6
            "CLUBS_7" -> R.drawable.clubs_7
            "CLUBS_8" -> R.drawable.clubs_8
            "CLUBS_9" -> R.drawable.clubs_9
            "CLUBS_10" -> R.drawable.clubs_10
            "SPADES_2" -> R.drawable.spades_2
            "SPADES_3" -> R.drawable.spades_3
            "SPADES_4" -> R.drawable.spades_4
            "SPADES_5" -> R.drawable.spades_5
            "SPADES_6" -> R.drawable.spades_6
            "SPADES_7" -> R.drawable.spades_7
            "SPADES_8" -> R.drawable.spades_8
            "SPADES_9" -> R.drawable.spades_9
            "SPADES_10" -> R.drawable.spades_10
            "DIAMONDS_2" -> R.drawable.diamonds_2
            "DIAMONDS_3" -> R.drawable.diamonds_3
            "DIAMONDS_4" -> R.drawable.diamonds_4
            "DIAMONDS_5" -> R.drawable.diamonds_5
            "DIAMONDS_6" -> R.drawable.diamonds_6
            "DIAMONDS_7" -> R.drawable.diamonds_7
            "DIAMONDS_8" -> R.drawable.diamonds_8
            "DIAMONDS_9" -> R.drawable.diamonds_9
            "DIAMONDS_10" -> R.drawable.diamonds_10
            "SPADES_of_jack" -> R.drawable.jack_of_spades
            "HEARTS_of_jack" -> R.drawable.jack_of_hearts
            "CLUBS_of_jack" -> R.drawable.jack_of_clubs
            "DIAMONDS_of_jack" -> R.drawable.jack_of_diamonds
            "SPADES_of_queen" -> R.drawable.queen_of_spades
            "HEARTS_of_queen" -> R.drawable.queen_of_hearts
            "CLUBS_of_queen" -> R.drawable.queen_of_clubs
            "DIAMONDS_of_queen" -> R.drawable.queen_of_diamonds
            "SPADES_of_king" -> R.drawable.king_of_spades
            "HEARTS_of_king" -> R.drawable.king_of_hearts
            "CLUBS_of_king" -> R.drawable.king_of_clubs
            "DIAMONDS_of_king" -> R.drawable.king_of_diamonds
            "ace_of_CLUBS" -> R.drawable.ace_of_clubs
            "ace_of_SPADES" -> R.drawable.ace_of_spades
            "ace_of_HEARTS" -> R.drawable.ace_of_hearts
            "ace_of_DIAMONDS" -> R.drawable.ace_of_diamonds
            else -> {1}
        }
    }
}