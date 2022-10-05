package com.sadadnan.boshontomovieplayer.model.search


import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("imdbID")
    var imdbID: String?, // tt0800369
    @SerializedName("Poster")
    var poster: String?, // https://m.media-amazon.com/images/M/MV5BOGE4NzU1YTAtNzA3Mi00ZTA2LTg2YmYtMDJmMThiMjlkYjg2XkEyXkFqcGdeQXVyNTgzMDMzMTg@._V1_SX300.jpg
    @SerializedName("Title")
    var title: String?, // Thor
    @SerializedName("Type")
    var type: String?, // movie
    @SerializedName("Year")
    var year: String? // 2011
)