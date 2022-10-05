package com.sadadnan.boshontomovieplayer.model.tmdb_details


import com.google.gson.annotations.SerializedName

data class TmdbDetailsModel(
    @SerializedName("adult")
    var adult: Boolean?, // false
    @SerializedName("backdrop_path")
    var backdropPath: String?, // /rr7E0NoGKxvbkb89eR1GwfoYjpA.jpg
    @SerializedName("belongs_to_collection")
    var belongsToCollection: Any?, // null
    @SerializedName("budget")
    var budget: Int?, // 63000000
    @SerializedName("genres")
    var genres: List<Genre?>?,
    @SerializedName("homepage")
    var homepage: String?, // http://www.foxmovies.com/movies/fight-club
    @SerializedName("id")
    var id: Int?, // 550
    @SerializedName("imdb_id")
    var imdbId: String?, // tt0137523
    @SerializedName("original_language")
    var originalLanguage: String?, // en
    @SerializedName("original_title")
    var originalTitle: String?, // Fight Club
    @SerializedName("overview")
    var overview: String?, // A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground "fight clubs" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.
    @SerializedName("popularity")
    var popularity: Double?, // 77.78
    @SerializedName("poster_path")
    var posterPath: String?, // /pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg
    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompany?>?,
    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountry?>?,
    @SerializedName("release_date")
    var releaseDate: String?, // 1999-10-15
    @SerializedName("revenue")
    var revenue: Int?, // 100853753
    @SerializedName("runtime")
    var runtime: Int?, // 139
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage?>?,
    @SerializedName("status")
    var status: String?, // Released
    @SerializedName("tagline")
    var tagline: String?, // Mischief. Mayhem. Soap.
    @SerializedName("title")
    var title: String?, // Fight Club
    @SerializedName("video")
    var video: Boolean?, // false
    @SerializedName("vote_average")
    var voteAverage: Double?, // 8.433
    @SerializedName("vote_count")
    var voteCount: Int? // 24892
)