package com.sadadnan.boshontomovieplayer.model.popular


import com.google.gson.annotations.SerializedName

data class PopularMovieModel(
    @SerializedName("page")
    var page: Int?, // 1
    @SerializedName("results")
    var popularResults: List<PopularResult?>?,
    @SerializedName("total_pages")
    var totalPages: Int?, // 35325
    @SerializedName("total_results")
    var totalResults: Int? // 706482
)