package io.github.garykam.blackjack.model

data class Card(
    val value: String,
    val suit: Int,
    var faceUp: Boolean
)