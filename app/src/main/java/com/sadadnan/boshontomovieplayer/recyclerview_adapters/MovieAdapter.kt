package com.sadadnan.boshontomovieplayer.recyclerview_adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sadadnan.boshontomovieplayer.R
import com.sadadnan.boshontomovieplayer.databinding.RowMovieBinding
import com.sadadnan.boshontomovieplayer.model.search.Search
import com.sadadnan.boshontomovieplayer.ui.details.DetailsFragment

class MovieAdapter(
    val context: Context?, private val mList: List<Search?>,
    private val listener: MovieItemClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private lateinit var binding : RowMovieBinding

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the cardview_search_result view
        // that is used to hold list item
        binding = RowMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mList[position]
        holder.itemView.setOnClickListener { view ->
            data?.let { it -> listener.onCLick(it) }
        }

        holder.bind(data)
    }

    private fun loadFragment(newInstance: Unit) {
        //load fragment

    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(private val binding: RowMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchModel: Search?){
            binding.movieItem = searchModel
        }
    }

    class MovieItemClickListener(val callback: (id: String) -> Unit){
        fun onCLick(tm:Search) = tm.imdbID?.let { callback(it) }
    }
}
