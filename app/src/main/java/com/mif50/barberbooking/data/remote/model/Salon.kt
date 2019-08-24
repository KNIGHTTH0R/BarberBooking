package com.mif50.barberbooking.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Salon(val name: String, val address: String,var salonId:String,var isTapped: Boolean = false): Parcelable{
    constructor():this("","","0")
}