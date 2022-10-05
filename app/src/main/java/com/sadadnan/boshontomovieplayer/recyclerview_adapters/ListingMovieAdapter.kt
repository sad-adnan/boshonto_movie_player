package com.sadadnan.boshontomovieplayer.recyclerview_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.sadadnan.boshontomovieplayer.databinding.RowListingMovieBinding
import com.sadadnan.boshontomovieplayer.model.search.Search
import com.sadadnan.boshontomovieplayer.network.Resource
import com.sadadnan.boshontomovieplayer.ui.main.MainViewModel

class ListingMovieAdapter(val context: Context?, private var mList: List<Search?>, private val viewModel: MainViewModel) : RecyclerView.Adapter<ListingMovieAdapter.ViewHolder>() {

    private lateinit var binding : RowListingMovieBinding

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the cardview_search_result view
        // that is used to hold list item
        binding = RowListingMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieViewModel = mList[position]

//        if (position == mList.size - 3) {
//            (binding.root.context as? LifecycleOwner)?.let { lo ->
//                viewModel.loadMoreBatmanMovies(mList.size/10).observe(lo){
//                    when(it.status){
//                        Resource.Status.SUCCESS -> {
//                            //show data
//                            val smm = it.data
//                            val data = smm?.search
//                            if (data != null) {
//                                if (data.isNotEmpty()){
//                                    //convert list to arrayList
//                                    val arrayList = ArrayList<Search?>()
//                                    arrayList.addAll(mList)
//                                    arrayList.addAll(data)
//                                    //convert arrayList to list
//                                    mList = arrayList.toList()
//                                    notifyDataSetChanged()
//                                }
//                            }
//                        }
//                        Resource.Status.ERROR -> {
//                            //show error
//                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
//                        }
//                        Resource.Status.LOADING -> {
//                            //show loading
//                            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            }
//        }

        holder.bind(movieViewModel)
    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(private val binding: RowListingMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchModel: Search?){
            binding.movieItem = searchModel
        }
    }
}
