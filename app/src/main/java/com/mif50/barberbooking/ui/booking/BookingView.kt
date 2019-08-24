package com.mif50.barberbooking.ui.booking

import com.mif50.barberbooking.base.mvp.BaseView
import com.mif50.barberbooking.data.remote.model.Barber

interface BookingView : BaseView {
    fun onShowBarbers(barbers: ArrayList<Barber>)
    fun onErrorBarber(messageError: String)
}