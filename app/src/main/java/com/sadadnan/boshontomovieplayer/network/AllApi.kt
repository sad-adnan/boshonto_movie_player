package com.sadadnan.boshontomovieplayer.network

class AllApi {

    companion object {
        //static OMDB Base Urls
        const val BASE_URL = "http://www.omdbapi.com/"
        const val BASE_URL_TMDB = "https://api.themoviedb.org/3/"
        const val API_KEY = "b9bd48a6"
        const val API_KEY_TMDB = "d0b624522dc0b39162b082f4b44bd5b2"

        //static Youtube Base Urls
        const val YOUTUBE_BASE_URL = "https://www.googleapis.com/youtube/v3/"
        //static Youtube API Key

        //URL end points

        //get featured movies from omdb
        const val FEATURED_MOVIES = "?apikey=b9bd48a6&s=thor"

        //get batman movies from omdb
        const val BATMAN_MOVIES = "?apikey=b9bd48a6&s=batman"

        //get Latest movies from tmdb
        const val POPULAR_MOVIES = "movie/popular"

        //get movie details from tmdb
        const val MOVIE_DETAILS = "movie/{movie_id}"

        //get movie details from omdb
        const val MOVIE_DETAILS_OMDB = "?apikey=b9bd48a6"

    }

}