package com.sadadnan.boshontomovieplayer.ui.main

import androidx.lifecycle.ViewModel
import com.sadadnan.boshontomovieplayer.network.repos.MovieRepository

class MainViewModel : ViewModel() {

    private val repository:MovieRepository = MovieRepository()

    fun getFeaturedMovies() = repository.getFeaturedMovies()

    fun getBatmanMovies() = repository.getBatmanMovies()

    fun getPopularMovies() = repository.getPopularMovies()

    fun loadMoreBatmanMovies(page: Int) = repository.loadMoreBatmanMovies(page)

    fun getTmdbMovieDetails(movieID: String) = repository.getTmdbMovieDetails(movieID)
}