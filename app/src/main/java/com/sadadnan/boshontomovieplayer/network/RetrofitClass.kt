package com.sadadnan.boshontomovieplayer.network

import com.sadadnan.boshontomovieplayer.network.service.MovieService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClass {
        companion object {

                //static Retrofit Instance
                private var retrofit: Retrofit? = null

//                //moshi converter
//                private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

                //get Retrofit Instance
                private fun getRetrofitInstance(): Retrofit? {

                        if (retrofit == null) {
                                retrofit = Retrofit.Builder()
                                        .baseUrl(AllApi.BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()
                        }
                        return retrofit
                }

                //create retrofit service
                fun createService(): MovieService? {
                        return getRetrofitInstance()?.create(MovieService::class.java)
                }

        }




}