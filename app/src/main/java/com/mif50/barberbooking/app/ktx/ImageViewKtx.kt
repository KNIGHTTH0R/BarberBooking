package com.mif50.barberbooking.app.ktx

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String){
    Picasso.get().load(url).into(this)
}