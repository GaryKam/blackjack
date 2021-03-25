package io.github.garykam.blackjack.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import io.github.garykam.blackjack.R
import io.github.garykam.blackjack.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(this)
            .asGif()
            .load(R.raw.card_animation)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    exception: GlideException?, model: Any?,
                    target: Target<GifDrawable>?, isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?, model: Any?, target: Target<GifDrawable>?,
                    dataSource: DataSource?, isFirstResource: Boolean
                ): Boolean {
                    resource?.setLoopCount(1)
                    binding.buttonPlay.apply {
                        isEnabled = true
                        animate().setStartDelay(2250L).alpha(1f).setDuration(500L).start()
                    }
                    return false
                }
            })
            .into(binding.imageCardAnimation)

        binding.buttonPlay.setOnClickListener {
            findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}