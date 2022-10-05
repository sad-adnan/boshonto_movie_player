package com.sadadnan.boshontomovieplayer.network.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.sadadnan.boshontomovieplayer.model.popular.PopularMovieModel
import com.sadadnan.boshontomovieplayer.model.popular.PopularResult
import com.sadadnan.boshontomovieplayer.model.search.SearchMovieModel
import com.sadadnan.boshontomovieplayer.network.AllApi
import com.sadadnan.boshontomovieplayer.network.Resource
import com.sadadnan.boshontomovieplayer.network.RetrofitClass
import com.sadadnan.boshontomovieplayer.network.TmdbRetrofitClass
import com.sadadnan.boshontomovieplayer.network.service.MovieService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository {
    companion object {
        const val tag = "MovieRepository"
        private var api: MovieService? = null
    }

    init {
       api = RetrofitClass.createService()
    }

    fun getFeaturedMovies() : LiveData<Resource<SearchMovieModel>> {
        // get featured movies from omdb

        val call = api?.getFeaturedMovies()
        val movieLiveData = MutableLiveData<Resource<SearchMovieModel>>()

        movieLiveData.value = Resource(Resource.Status.LOADING,null,"Loading")

        call?.enqueue(object : Callback<SearchMovieModel?> {
            override fun onResponse(
                call: Call<SearchMovieModel?>?,
                response: Response<SearchMovieModel?>?
            ) {
                if (response?.isSuccessful!!) {
                    val searchMovieModel: SearchMovieModel? = response.body()
                    if (searchMovieModel != null) {
                        Log.d(tag, "onResponse: " + searchMovieModel.toString())
                        //set featured movies to recyclerview
                        movieLiveData.value = Resource(Resource.Status.SUCCESS,searchMovieModel,"Data Loaded")
                    }else{
                        movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
                    }
                }else{
                    movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
                }
            }

            override fun onFailure(call: Call<SearchMovieModel?>?, t: Throwable?) {
                Log.d(tag, "onFailure: " + t?.message)
                movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
            }
        })

        return movieLiveData
    }

    fun getBatmanMovies() : LiveData<Resource<SearchMovieModel>> {
        // get featured movies from omdb

        val call = api?.getBatmanMovies()
        val movieLiveData = MutableLiveData<Resource<SearchMovieModel>>()

        movieLiveData.value = Resource(Resource.Status.LOADING,null,"Loading")

        call?.enqueue(object : Callback<SearchMovieModel?> {
            override fun onResponse(
                call: Call<SearchMovieModel?>?,
                response: Response<SearchMovieModel?>?
            ) {
                if (response?.isSuccessful!!) {
                    val searchMovieModel: SearchMovieModel? = response.body()
                    if (searchMovieModel != null) {
                        Log.d(tag, "onResponse: " + searchMovieModel.toString())
                        //set featured movies to recyclerview
                        movieLiveData.value = Resource(Resource.Status.SUCCESS,searchMovieModel,"Data Loaded")
                    }else{
                        movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
                    }
                }else{
                    movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
                }
            }

            override fun onFailure(call: Call<SearchMovieModel?>?, t: Throwable?) {
                Log.d(tag, "onFailure: " + t?.message)
                movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
            }
        })

        return movieLiveData
    }

    fun getPopularMovies() : LiveData<Resource<PopularMovieModel>> {
        // get featured movies from omdb
        val tmdbApi = TmdbRetrofitClass.createService()
        val call = tmdbApi?.getPopularMovies(AllApi.API_KEY_TMDB,"en-US",1,"US","3")
        val movieLiveData = MutableLiveData<Resource<PopularMovieModel>>()

        movieLiveData.value = Resource(Resource.Status.LOADING,null,"Loading")

        call?.enqueue(object : Callback<PopularMovieModel> {
            override fun onResponse(
                call: Call<PopularMovieModel?>?,
                response: Response<PopularMovieModel?>?
            ) {
                if (response?.isSuccessful!!) {
                    val popularMovieModel: PopularMovieModel? = response.body()
                    if (popularMovieModel != null) {
                        Log.d(tag, "onResponse: " + popularMovieModel.toString())
                        //set featured movies to recyclerview
                        movieLiveData.value = Resource(Resource.Status.SUCCESS,popularMovieModel,"Data Loaded")
                    }else{
                        movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
                    }
                }else{
                    movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
                }
            }

            override fun onFailure(call: Call<PopularMovieModel?>?, t: Throwable?) {
                Log.d(tag, "onFailure: " + t?.message)
                movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
            }
        })

        return movieLiveData
    }

    fun loadMoreBatmanMovies(page: Int): LiveData<Resource<SearchMovieModel>> {
        val call = api?.getMoreBatmanMovies(page)
        val movieLiveData = MutableLiveData<Resource<SearchMovieModel>>()

        movieLiveData.value = Resource(Resource.Status.LOADING,null,"Loading")

        call?.enqueue(object : Callback<SearchMovieModel?> {
            override fun onResponse(
                call: Call<SearchMovieModel?>?,
                response: Response<SearchMovieModel?>?
            ) {
                if (response?.isSuccessful!!) {
                    val searchMovieModel: SearchMovieModel? = response.body()
                    if (searchMovieModel != null) {
                        Log.d(tag, "onResponse: $searchMovieModel")
                        //set featured movies to recyclerview
                        movieLiveData.value = Resource(Resource.Status.SUCCESS,searchMovieModel,"Data Loaded")
                    }else{
                        movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
                    }
                }else{
                    movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
                }
            }

            override fun onFailure(call: Call<SearchMovieModel?>?, t: Throwable?) {
                Log.d(tag, "onFailure: " + t?.message)
                movieLiveData.value = Resource(Resource.Status.ERROR,null,"Data not found")
            }
        })

        return movieLiveData
    }
}