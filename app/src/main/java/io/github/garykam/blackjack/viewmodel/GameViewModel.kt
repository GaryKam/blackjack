package io.github.garykam.blackjack.viewmodel

import androidx.lifecycle.ViewModel
import io.github.garykam.blackjack.R
import io.github.garykam.blackjack.model.Card

class GameViewModel: ViewModel() {
    private val cardValues = mapOf(
        "A" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5, "6" to 6, "7" to 7,
        "8" to 8, "9" to 9, "10" to 10, "J" to 10, "Q" to 10, "K" to 10
    )
    private val cardSuits = setOf(
        R.drawable.ic_spade, R.drawable.ic_heart, R.drawable.ic_diamond, R.drawable.ic_clubs
    )
    private val dealerCards = mutableSetOf<Card>()
    private val playerCards = mutableSetOf<Card>()

    fun hit(dealer: Boolean, faceUp: Boolean = true): Card {
        var card = Card(cardValues.keys.random(), cardSuits.random(), faceUp)
        while (dealerCards.contains(card) || playerCards.contains(card)) {
            card = Card(cardValues.keys.random(), cardSuits.random(), faceUp)
        }

        if (dealer) {
            dealerCards.add(card)
        } else {
            playerCards.add(card)
        }

        return card
    }
}