package com.sadadnan.boshontomovieplayer.binding_adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.sadadnan.boshontomovieplayer.R
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat




@BindingAdapter("app:toString")
fun TextView.convertToString(num : Int){
    text = num.toString()
}

@BindingAdapter("app:setOnlineImage")
fun ImageView.setOnlineImage(url: String){
    try {
        try {
            Picasso.get().load(url).placeholder(R.mipmap.ic_launcher).fit().into(this)
        } catch (e: Exception) {
        }
    } catch (e: Exception) {
    }
}


@BindingAdapter("app:floatToString")
fun TextView.floatToString(float: Float){
    "$float".also { text = it }
}



