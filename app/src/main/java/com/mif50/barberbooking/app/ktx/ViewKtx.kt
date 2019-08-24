package com.mif50.barberbooking.app.ktx

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

fun EditText.textValue(): String{
    return this.text.toString().trim()
}

fun View.setVisiable(){
    this.visibility = View.VISIBLE
}

fun View.setGone(){
    this.visibility = View.GONE
}

fun ViewGroup.inflateLayout(resView: Int): View = LayoutInflater.from(this.context).inflate(resView,this,false)

fun Context.getColorRes(resColor: Int):Int = ContextCompat.getColor(this, resColor)

fun TextView.changeTextColor(colorId: Int){
    this.setTextColor(this.context.resources.getColor(colorId))
}