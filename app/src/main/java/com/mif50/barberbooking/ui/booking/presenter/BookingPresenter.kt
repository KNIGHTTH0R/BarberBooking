package com.mif50.barberbooking.ui.booking.presenter

import com.mif50.barberbooking.base.mvp.BasePresenter
import com.mif50.barberbooking.base.mvp.BaseView

interface BookingPresenter<T:BaseView>: BasePresenter<T> {
    fun loadBarbers(salonId: String)
}