package com.sadadnan.boshontomovieplayer.ui.details

import android.os.Bundle
import android.util.Log
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


    private val playbackPosition get() = GlobalVar.playbackPosition
    private var movieID: String? = null

    var player: ExoPlayer? = null

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

            //releasePlayer()

            playVideo2(GlobalVar.demoDashUrl)
        }
    }



    private fun playVideo2(dashUri: String) {
        val player = ExoPlayer.Builder(requireContext()).build()
        player.playWhenReady = true
        binding.exoPlayerView.player = player
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaItem = MediaItem.fromUri(dashUri)
        val mediaSource = DashMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(mediaItem)
        player.setMediaSource(mediaSource)
        Log.e("playback", "playVideo2-103: ${GlobalVar.playbackPosition}", )
        player.seekTo(playbackPosition)
//        exoPlayer.playWhenReady = playWhenReady
        player.prepare()
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

    override fun onResume() {
        binding.exoPlayerView.player?.playWhenReady = true
        binding.exoPlayerView.player?.seekTo(playbackPosition)
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
        binding.exoPlayerView.player?.pause()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player = binding.exoPlayerView.player as ExoPlayer?
        if (player != null) {
            val playWhenReady = player!!.playWhenReady
            Log.e("playback", "playVideo2-before: ${GlobalVar.playbackPosition}" )
            GlobalVar.playbackPosition = player!!.currentPosition
            Log.e("playback", "playVideo2-after: ${GlobalVar.playbackPosition}", )
            //player!!.release()
            Log.e("playback", "playVideo2-later: ${GlobalVar.playbackPosition}", )
        }
    }

}