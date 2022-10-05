package com.sadadnan.boshontomovieplayer.network

import com.sadadnan.boshontomovieplayer.R

class MovieRatingIcon {
    companion object {
        fun getRatingIcon(rating: String): Int {
            return when (rating) {
                "G" -> {
                    R.drawable.ic_g
                }
                "PG" -> {
                    R.drawable.ic_pg
                }
                "PG-13" -> {
                    R.drawable.ic_pg13
                }
                "R" -> {
                    R.drawable.ic_r
                }
                "NC-17" -> {
                    R.drawable.ic_nc17
                }
                else -> {
                    R.drawable.ic_g
                }
            }
        }
    }
}