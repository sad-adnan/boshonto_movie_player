package com.sadadnan.boshontomovieplayer.model.tmdb_details


import com.google.gson.annotations.SerializedName

data class ProductionCompany(
    @SerializedName("id")
    var id: Int?, // 508
    @SerializedName("logo_path")
    var logoPath: String?, // /7cxRWzi4LsVm4Utfpr1hfARNurT.png
    @SerializedName("name")
    var name: String?, // Regency Enterprises
    @SerializedName("origin_country")
    var originCountry: String? // US
)