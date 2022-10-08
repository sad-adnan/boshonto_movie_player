package com.sadadnan.boshontomovieplayer.network.service

import com.sadadnan.boshontomovieplayer.model.details.MovieDetailsModel
import com.sadadnan.boshontomovieplayer.model.popular.PopularMovieModel
import com.sadadnan.boshontomovieplayer.model.search.SearchMovieModel
import com.sadadnan.boshontomovieplayer.model.tmdb_details.TmdbDetailsModel
import com.sadadnan.boshontomovieplayer.network.AllApi
import com.sadadnan.boshontomovieplayer.network.HttpParam
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    //get featured movies from omdb
    @GET(AllApi.FEATURED_MOVIES)
    fun getFeaturedMovies(): Call<SearchMovieModel?>?

    //get batman movies from omdb
    @GET(AllApi.BATMAN_MOVIES)
    fun getMoreBatmanMovies(
        @Query(HttpParam.PAGE) page: Int
    ): Call<SearchMovieModel?>?

    @GET(AllApi.BATMAN_MOVIES)
    fun getBatmanMovies(): Call<SearchMovieModel?>?

    @GET(AllApi.MOVIE_DETAILS_OMDB)
    fun getMovieDetails(
        @Query(HttpParam.I) imdbId: String
    ): Call<MovieDetailsModel?>?

    //get Latest movies from tmdb
    @GET(AllApi.POPULAR_MOVIES)
    fun getPopularMovies(@Query(HttpParam.API_KEY) apiKey: String,
                         @Query(HttpParam.LANGUAGE) language: String,
                         @Query(HttpParam.PAGE) pageNumber: Int,
                         @Query(HttpParam.REGION) region:String,
                         @Query(HttpParam.WITH_RELEASE_TYPE) releaseType: String): Call<PopularMovieModel>

    @GET(AllApi.MOVIE_DETAILS)
    fun getMovieImdbId(@Path(HttpParam.MOVIE_ID) movieId: String,
                       @Query(HttpParam.API_KEY) apiKey: String): Call<TmdbDetailsModel>
}