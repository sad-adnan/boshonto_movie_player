package com.sadadnan.boshontomovieplayer.network.service

import com.sadadnan.boshontomovieplayer.model.details.MovieDetailsModel
import com.sadadnan.boshontomovieplayer.model.popular.PopularMovieModel
import com.sadadnan.boshontomovieplayer.model.search.SearchMovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    //get featured movies from omdb
    @GET("?apikey=b9bd48a6&s=thor")
    fun getFeaturedMovies(): Call<SearchMovieModel?>?

    //get batman movies from omdb
    @GET("?apikey=b9bd48a6&s=batman")
    fun getMoreBatmanMovies(
        @Query("page") page: Int
    ): Call<SearchMovieModel?>?

    @GET("?apikey=b9bd48a6&s=batman")
    fun getBatmanMovies(): Call<SearchMovieModel?>?

    @GET("?apikey=b9bd48a6")
    fun getMovieDetails(
        @Query("i") imdbId: String
    ): Call<MovieDetailsModel?>?

    //get Latest movies from tmdb
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String,
                         @Query("language") language: String,
                         @Query("page") pageNumber: Int,
                         @Query("region") region:String,
                         @Query("with_release_type") releaseType: String): Call<PopularMovieModel>
}