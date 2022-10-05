package com.sadadnan.boshontomovieplayer.model.details


import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("Source")
    var source: String?, // Internet Movie Database
    @SerializedName("Value")
    var value: String? // 7.1/10
)