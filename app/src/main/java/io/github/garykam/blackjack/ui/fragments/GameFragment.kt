package io.github.garykam.blackjack.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.garykam.blackjack.databinding.FragmentGameBinding
import io.github.garykam.blackjack.model.Dealer

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var dealer: Dealer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dealer = Dealer(context)
        binding.tablePlayer.addView(dealer.hit())
        binding.tableOpponent.addView(dealer.hit())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}