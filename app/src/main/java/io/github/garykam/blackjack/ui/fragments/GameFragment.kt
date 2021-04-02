package io.github.garykam.blackjack.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.garykam.blackjack.R
import io.github.garykam.blackjack.databinding.FragmentGameBinding
import io.github.garykam.blackjack.model.Card
import io.github.garykam.blackjack.viewmodel.GameViewModel

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(GameViewModel::class.java)

        hit(dealer = true)
        hit(dealer = true, faceUp = false)
        hit(dealer = false)
        hit(dealer = false)

        binding.buttonHit.setOnClickListener { hit(dealer = false) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hit(dealer: Boolean, faceUp: Boolean = true) {
        val card: Card = viewModel.hit(dealer, faceUp)
        val playingCard: View = if (faceUp) {
            LayoutInflater.from(context).inflate(R.layout.playing_card, null).apply {
                findViewById<TextView>(R.id.text_value).text = card.value
                findViewById<ImageView>(R.id.image_suit).setImageResource(card.suit)
            }
        } else {
            LayoutInflater.from(context).inflate(R.layout.playing_card_down, null)
        }

        if (dealer) {
            binding.tableDealer.addView(playingCard)
        } else {
            binding.tablePlayer.addView(playingCard)
        }
    }
}