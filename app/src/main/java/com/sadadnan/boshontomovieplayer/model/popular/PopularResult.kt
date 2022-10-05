package com.sadadnan.boshontomovieplayer.model.popular


import com.google.gson.annotations.SerializedName

data class PopularResult(
    @SerializedName("adult")
    var adult: Boolean?, // false
    @SerializedName("backdrop_path")
    var backdropPath: String?, // /y2Ca1neKke2mGPMaHzlCNDVZqsK.jpg
    @SerializedName("genre_ids")
    var genreIds: List<Int?>?,
    @SerializedName("id")
    var id: Int?, // 718930
    @SerializedName("original_language")
    var originalLanguage: String?, // en
    @SerializedName("original_title")
    var originalTitle: String?, // Bullet Train
    @SerializedName("overview")
    var overview: String?, // Unlucky assassin Ladybug is determined to do his job peacefully after one too many gigs gone off the rails. Fate, however, may have other plans, as Ladybug's latest mission puts him on a collision course with lethal adversaries from around the globe—all with connected, yet conflicting, objectives—on the world's fastest train.
    @SerializedName("popularity")
    var popularity: Double?, // 6494.33
    @SerializedName("poster_path")
    var posterPath: String?, // /tVxDe01Zy3kZqaZRNiXFGDICdZk.jpg
    @SerializedName("release_date")
    var releaseDate: String?, // 2022-07-03
    @SerializedName("title")
    var title: String?, // Bullet Train
    @SerializedName("video")
    var video: Boolean?, // false
    @SerializedName("vote_average")
    var voteAverage: Double?, // 7.5
    @SerializedName("vote_count")
    var voteCount: Int? // 1304
)