package com.sadadnan.boshontomovieplayer.model.details


import com.google.gson.annotations.SerializedName

data class MovieDetailsModel(
    @SerializedName("Actors")
    var actors: String?, // Amy Adams, Ben Affleck, Henry Cavill
    @SerializedName("Awards")
    var awards: String?, // 2 nominations
    @SerializedName("BoxOffice")
    var boxOffice: String?, // N/A
    @SerializedName("Country")
    var country: String?, // United States
    @SerializedName("DVD")
    var dVD: String?, // N/A
    @SerializedName("Director")
    var director: String?, // Zack Snyder
    @SerializedName("Genre")
    var genre: String?, // Action, Adventure, Sci-Fi
    @SerializedName("imdbID")
    var imdbID: String?, // tt18689424
    @SerializedName("imdbRating")
    var imdbRating: String?, // 7.1
    @SerializedName("imdbVotes")
    var imdbVotes: String?, // 56,021
    @SerializedName("Language")
    var language: String?, // English
    @SerializedName("Metascore")
    var metascore: String?, // N/A
    @SerializedName("Plot")
    var plot: String?, // Batman is manipulated by Lex Luthor to fear Superman. Superman´s existence is meanwhile dividing the world and he is framed for murder during an international crisis. The heroes clash and force the neutral Wonder Woman to reemerge.
    @SerializedName("Poster")
    var poster: String?, // https://m.media-amazon.com/images/M/MV5BN2I4OTllM2MtMWVhNC00MjkzLWJlMDUtN2FhMGQ2ZGVjMjllXkEyXkFqcGdeQXVyMTEyNzgwMDUw._V1_SX300.jpg
    @SerializedName("Production")
    var production: String?, // N/A
    @SerializedName("Rated")
    var rated: String?, // R
    @SerializedName("Ratings")
    var ratings: List<Rating?>?,
    @SerializedName("Released")
    var released: String?, // 23 Mar 2016
    @SerializedName("Response")
    var response: String?, // True
    @SerializedName("Runtime")
    var runtime: String?, // 182 min
    @SerializedName("Title")
    var title: String?, // Batman v Superman: Dawn of Justice - Ultimate Edition
    @SerializedName("Type")
    var type: String?, // movie
    @SerializedName("Website")
    var website: String?, // N/A
    @SerializedName("Writer")
    var writer: String?, // David S. Goyer, Chris Terrio
    @SerializedName("Year")
    var year: String? // 2016
)