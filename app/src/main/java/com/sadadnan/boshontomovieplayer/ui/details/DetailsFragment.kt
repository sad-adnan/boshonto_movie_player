package com.sadadnan.boshontomovieplayer.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sadadnan.boshontomovieplayer.R
import com.sadadnan.boshontomovieplayer.binding_adapters.setOnlineImage
import com.sadadnan.boshontomovieplayer.databinding.FragmentDetailsBinding
import com.sadadnan.boshontomovieplayer.databinding.FragmentListingBinding
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
    }

    private fun observe() {
        viewModel.getMovieDetails(movieID!!).observe(viewLifecycleOwner) { mdm ->
            when(mdm.status){
                Resource.Status.SUCCESS -> {
                    binding.movieItem = mdm.data
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

}