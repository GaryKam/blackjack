package io.github.garykam.blackjack.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.github.garykam.blackjack.R

class Dealer(private val context: Context?) {
    private val cardValues = mapOf(
        "A" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5, "6" to 6, "7" to 7,
        "8" to 8, "9" to 9, "10" to 10, "J" to 10, "Q" to 10, "K" to 10
    )
    private val cardSuits = setOf(
        R.drawable.ic_spade, R.drawable.ic_heart, R.drawable.ic_diamond, R.drawable.ic_clubs
    )

    fun hit(): View {
        val playingCard: View = LayoutInflater.from(context).inflate(R.layout.playing_card, null)
        val value: TextView = playingCard.findViewById(R.id.text_value)
        value.text = cardValues.keys.random()
        val symbol: ImageView = playingCard.findViewById(R.id.image_symbol)
        symbol.setImageResource(cardSuits.random())
        return playingCard
    }
}