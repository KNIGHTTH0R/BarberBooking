package com.mif50.barberbooking.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Barber(
    val name: String,
    val username: String,
    var password: String = "",
    val rating: Long,
    var BarberId: String = "0",
    var isTapped: Boolean = false
) : Parcelable {
    constructor():this("","","",0,"0",false)
}