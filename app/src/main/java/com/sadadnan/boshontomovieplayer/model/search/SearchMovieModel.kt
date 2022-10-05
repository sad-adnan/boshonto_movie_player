package com.sadadnan.boshontomovieplayer.model.search


import com.google.gson.annotations.SerializedName

data class SearchMovieModel(
    @SerializedName("Response")
    var response: String?, // True
    @SerializedName("Search")
    var search: List<Search?>?,
    @SerializedName("totalResults")
    var totalResults: String? // 85
)