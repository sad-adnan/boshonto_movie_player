package com.sadadnan.boshontomovieplayer.ui.details

import androidx.lifecycle.ViewModel
import com.sadadnan.boshontomovieplayer.network.repos.MovieRepository

class DetailsViewModel : ViewModel() {
    private val repository: MovieRepository = MovieRepository()

    fun getMovieDetails(movieID: String) = repository.getMovieDetails(movieID)

    fun getTmdbMovieDetails(movieID: String) = repository.getTmdbMovieDetails(movieID)


}