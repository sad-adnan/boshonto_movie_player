package com.sadadnan.boshontomovieplayer.model.tmdb_details


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    var id: Int?, // 18
    @SerializedName("name")
    var name: String? // Drama
)