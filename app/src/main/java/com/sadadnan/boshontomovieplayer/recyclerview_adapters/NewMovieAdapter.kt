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
import com.sadadnan.boshontomovieplayer.databinding.RowPopularMovieBinding
import com.sadadnan.boshontomovieplayer.model.popular.PopularResult
import com.sadadnan.boshontomovieplayer.model.search.Search

class NewMovieAdapter(val context: Context?, private val mList: List<PopularResult?>,
                      private val listener: NewMovieItemClickListener
) : RecyclerView.Adapter<NewMovieAdapter.ViewHolder>() {

    private lateinit var binding : RowPopularMovieBinding

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the cardview_search_result view
        // that is used to hold list item
        binding = RowPopularMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)

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


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(private val binding: RowPopularMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(popularModel: PopularResult?){
            binding.movieItem = popularModel
//            binding.posterUrl = "https://image.tmdb.org/t/p/w500"+popularModel?.posterPath
            binding.posterUrl = "https://image.tmdb.org/t/p/original/"+popularModel?.posterPath
        }
    }

    class NewMovieItemClickListener(val callback: (id: String) -> Unit){
        fun onCLick(pm:PopularResult) = pm.id?.let { callback(it.toString()) }
    }
}
