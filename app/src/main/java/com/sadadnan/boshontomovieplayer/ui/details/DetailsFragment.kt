package com.sadadnan.boshontomovieplayer.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.sadadnan.boshontomovieplayer.GlobalVar
import com.sadadnan.boshontomovieplayer.databinding.FragmentDetailsBinding
import com.sadadnan.boshontomovieplayer.network.MovieRatingIcon
import com.sadadnan.boshontomovieplayer.network.Resource


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private val MOVIE_ID = "movieID"

class DetailsFragment : Fragment() {



    private var movieID: String? = null
    companion object {
        fun newInstance(movieID: String) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putString(MOVIE_ID, movieID)
            }
        }
    }

    private var _binding: FragmentDetailsBinding? = null
    private lateinit var viewModel: DetailsViewModel

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        arguments?.let {
            movieID = it.getString(MOVIE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        clickListeners()

    }

    private fun clickListeners() {
        binding.backBtn.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.watchOnlineBtn.setOnClickListener{
            binding.movieBannerIV.visibility = View.GONE
            binding.exoPlayerView.visibility = View.VISIBLE
            binding.watchOnlineBtn.visibility = View.GONE

            releasePlayer()

            playVideo2(GlobalVar.demoDashUrl)
        }
    }



    private fun playVideo2(dashUri: String) {
        val exoPlayer = ExoPlayer.Builder(requireContext()).build()
        exoPlayer.playWhenReady = true
        binding.exoPlayerView.player = exoPlayer
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaItem = MediaItem.fromUri(dashUri)
        val mediaSource = DashMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(mediaItem)
        exoPlayer.setMediaSource(mediaSource)
//        exoPlayer.seekTo(playbackPosition)
//        exoPlayer.playWhenReady = playWhenReady
        exoPlayer.prepare()
    }

    private fun observe() {
        viewModel.getMovieDetails(movieID!!).observe(viewLifecycleOwner) { mdm ->
            when(mdm.status){
                Resource.Status.SUCCESS -> {
                    binding.movieItem = mdm.data

                    val img = MovieRatingIcon.getRatingIcon(mdm.data?.rated!!)

                    binding.ratingIV.setImageDrawable(ResourcesCompat.getDrawable(resources, img, null))
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(activity, "Error: ${mdm.message}", Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    Toast.makeText(activity, "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStop() {
        super.onStop()
        binding.exoPlayerView.player?.stop()
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    private fun releasePlayer() {
        var player = binding.exoPlayerView.player
        if (player != null) {
            val playWhenReady = player.playWhenReady
            val playbackPosition = player.currentPosition
            player.release();
            player = null;
        }
    }

}