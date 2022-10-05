package com.sadadnan.boshontomovieplayer.model.tmdb_details


import com.google.gson.annotations.SerializedName

data class SpokenLanguage(
    @SerializedName("english_name")
    var englishName: String?, // English
    @SerializedName("iso_639_1")
    var iso6391: String?, // en
    @SerializedName("name")
    var name: String? // English
)