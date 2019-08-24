package com.mif50.barberbooking.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TimeSlot(val slot:Long, var isTapped: Boolean = false): Parcelable {
    constructor():this(0,false)
}