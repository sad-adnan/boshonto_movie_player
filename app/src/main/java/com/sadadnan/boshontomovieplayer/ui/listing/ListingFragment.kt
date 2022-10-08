package com.sadadnan.boshontomovieplayer.ui.listing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadadnan.boshontomovieplayer.R
import com.sadadnan.boshontomovieplayer.databinding.FragmentListingBinding
import com.sadadnan.boshontomovieplayer.databinding.FragmentMainBinding
import com.sadadnan.boshontomovieplayer.network.Resource
import com.sadadnan.boshontomovieplayer.recyclerview_adapters.ListingMovieAdapter
import com.sadadnan.boshontomovieplayer.recyclerview_adapters.MovieAdapter
import com.sadadnan.boshontomovieplayer.ui.details.DetailsFragment
import com.sadadnan.boshontomovieplayer.ui.main.MainViewModel

class ListingFragment : Fragment() {

    companion object {
        fun newInstance() = ListingFragment()
    }

    private var _binding: FragmentListingBinding? = null
    private lateinit var viewModel: MainViewModel

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListingBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        clickListeners()

        initializeRecyclerView()
    }

    private fun clickListeners() {
        binding.backBtn.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initializeRecyclerView() {
        binding.listingRecyclerView.layoutManager = GridLayoutManager(activity,2, LinearLayoutManager.VERTICAL, false)
    }

    private fun observe() {
        viewModel.getBatmanMovies().observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.SUCCESS -> {
                    //show data
                    val smm = it.data
                    val data = smm?.search
                    if (data != null) {
                        if (data.isNotEmpty()){
                            val movieAdapter = ListingMovieAdapter(context,data,viewModel,
                                MovieAdapter.MovieItemClickListener {movieId ->
                                    //load movie details fragment with imdbID
                                    val transection =  activity?.supportFragmentManager?.beginTransaction()
                                    transection?.replace(R.id.container, DetailsFragment.newInstance(movieId))
                                    transection?.addToBackStack("mainFragment")
                                    transection?.commitAllowingStateLoss()
                            })
                            binding.listingRecyclerView.adapter = movieAdapter
                            movieAdapter.notifyDataSetChanged()
                        }
                    }

                }
                Resource.Status.ERROR -> {
                    //show error
                    Toast.makeText(activity,"Error ${it.message}", Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    //show loading
                    Toast.makeText(activity,"Loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}