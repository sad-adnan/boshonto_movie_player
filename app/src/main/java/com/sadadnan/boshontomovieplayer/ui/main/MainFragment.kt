package com.sadadnan.boshontomovieplayer.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.sadadnan.boshontomovieplayer.R
import com.sadadnan.boshontomovieplayer.activity.MainActivity
import com.sadadnan.boshontomovieplayer.databinding.FragmentMainBinding
import com.sadadnan.boshontomovieplayer.model.search.Search
import com.sadadnan.boshontomovieplayer.model.search.SearchMovieModel
import com.sadadnan.boshontomovieplayer.network.Resource
import com.sadadnan.boshontomovieplayer.recyclerview_adapters.MovieAdapter
import com.sadadnan.boshontomovieplayer.recyclerview_adapters.MovieAdapter.*
import com.sadadnan.boshontomovieplayer.recyclerview_adapters.NewMovieAdapter
import com.sadadnan.boshontomovieplayer.ui.details.DetailsFragment
import com.sadadnan.boshontomovieplayer.ui.listing.ListingFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: FragmentMainBinding? = null
    private lateinit var viewModel: MainViewModel
    val imageList = ArrayList<SlideModel>() // Create image list

    var featuredMovieList: List<Search?>? = null



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
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //remove action bar
        (activity as MainActivity?)!!.supportActionBar!!.hide()

        initializeRecyclerView()

        observe()

        clickListeners()

    }

    private fun clickListeners() {

        binding.imageSlider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                Toast.makeText(context, "Position: $position", Toast.LENGTH_SHORT).show()
            }
        })

        binding.batmanMore.setOnClickListener {
            //navigate to ListingFragment
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, ListingFragment.newInstance())?.addToBackStack("mainFragment")
                ?.commitNow()
        }
    }



    private fun initializeRecyclerView() {
        binding.batmanRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.latestRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun observe() {
        viewModel.getFeaturedMovies().observe(viewLifecycleOwner) {

            when(it.status){
                Resource.Status.SUCCESS -> {
                    //show data
                    val smm = it.data
                    val data = smm?.search
                    featuredMovieList = data
                    //have only 5 data
                    for (i in 0..4){
                        val movie = data?.get(i)
                        val title = movie?.title
                        val year = movie?.year
                        val poster = movie?.poster
                        val imdbID = movie?.imdbID
                        val type = movie?.type

                        imageList.add(SlideModel(data?.get(i)?.poster, "$title ($year)"))

                    }
                    binding.imageSlider.setImageList(imageList)

                }
                Resource.Status.ERROR -> {
                    //show error
                    Toast.makeText(activity,"Error ${it.message}",Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    //show loading
                    Toast.makeText(activity,"Loading",Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getBatmanMovies().observe(viewLifecycleOwner){ it ->
            when(it.status){
                Resource.Status.SUCCESS -> {
                    //show data
                    val smm = it.data
                    val data = smm?.search
                    if (data != null) {
                        if (data.isNotEmpty()){
                            val movieAdapter = MovieAdapter(context,data, MovieItemClickListener { movieId ->
                                //load movie details fragment with imdbID
                               val transection =  activity?.supportFragmentManager?.beginTransaction()
                                transection?.replace(R.id.container, DetailsFragment.newInstance(movieId))
                                transection?.addToBackStack("mainFragment")
                                transection?.commitAllowingStateLoss()
                            })
                            binding.batmanRecyclerView.adapter = movieAdapter
                            movieAdapter.notifyDataSetChanged()
                        }
                    }

                }
                Resource.Status.ERROR -> {
                    //show error
                    Toast.makeText(activity,"Error ${it.message}",Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    //show loading
                    Toast.makeText(activity,"Loading",Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getPopularMovies().observe(viewLifecycleOwner){
            when(it.status){
                Resource.Status.SUCCESS -> {
                    //show data
                    val pmm = it.data
                    val data = pmm?.popularResults
                    if (data != null) {
                        if (data.isNotEmpty()){
                            val movieAdapter = NewMovieAdapter(context,data)
                            binding.latestRecyclerView.adapter = movieAdapter
                            movieAdapter.notifyDataSetChanged()
                        }
                    }

                }
                Resource.Status.ERROR -> {
                    //show error
                    Toast.makeText(activity,"Error ${it.message}",Toast.LENGTH_SHORT).show()
                }
                Resource.Status.LOADING -> {
                    //show loading
                    Toast.makeText(activity,"Loading",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}