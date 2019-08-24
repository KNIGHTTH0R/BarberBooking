package com.mif50.barberbooking.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(var name: String,var address:String, var phoneNumber: String): Parcelable {
    constructor(): this("","","")
}