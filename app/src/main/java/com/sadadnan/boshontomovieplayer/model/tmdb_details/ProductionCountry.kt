package com.sadadnan.boshontomovieplayer.model.tmdb_details


import com.google.gson.annotations.SerializedName

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    var iso31661: String?, // DE
    @SerializedName("name")
    var name: String? // Germany
)