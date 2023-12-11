package com.example.blackjack

import kotlin.random.Random

class Game {
    enum class Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    class Card(val suit: Suit, val value: Int, val imageName: String) {
        fun isAce(): Boolean {
            return value == 1
        }

        fun isFaceCard(): Boolean {
            return value in 11..13
        }
    }

    val deck = mutableListOf<Card>()
    val playerHand = mutableListOf<Card>()
    val dealerHand = mutableListOf<Card>()

    init {
        createDeck()
    }

    fun createDeck() {
        val suits = listOf(Suit.SPADES, Suit.HEARTS, Suit.DIAMONDS, Suit.CLUBS)

        for (suit in suits) {
            for (value in 1..13) {
                var imageName = "${suit}_${value}"
                val cardValue: Int
                if (value == 11) {
                    imageName = "ace_of_${suit}"
                    cardValue = value
                } else if (value in 11..13) {
                    val faceName = when (value) {
                        11 -> "jack"
                        12 -> "queen"
                        else -> "king"
                    }
                    imageName = "${suit}_of_${faceName}"
                    cardValue = 10
                } else {
                    cardValue = value
                }

                val card = Card(suit, cardValue, imageName)
                deck.add(card)
            }
        }
    }

    fun dealCards() {
        val random = Random
        for (i in 1..2) {
            playerHand.add(deck.removeAt(random.nextInt(deck.size)))
            dealerHand.add(deck.removeAt(random.nextInt(deck.size)))
        }
    }

    fun calculateScore(hand: List<Card>): Int {
        var totalScore = 0
        var aces = 0

        for (card in hand) {
            when {
                card.isAce() -> {
                    aces += 1
                    totalScore += 11
                }
                card.isFaceCard() -> totalScore += 10
                else -> totalScore += card.value
            }
        }
        while (totalScore > 21 && aces > 0) {
            totalScore -= 10
            aces -= 1
        }
        return totalScore
    }

    fun determineWinner(playerHand: List<Card>, dealerHand: List<Card>): String {
        val playerScore = calculateScore(playerHand)
        val dealerScore = calculateScore(dealerHand)

        return when {
            playerScore > 21 -> "THE DEALER!🎉"
            dealerScore > 21 -> "YOU!🥳🏆"
            playerScore > dealerScore -> "YOU!🥳🏆"
            dealerScore > playerScore -> "THE DEALER!🎉"
            else -> "NOBODY!😲🤝"
        }
    }
}