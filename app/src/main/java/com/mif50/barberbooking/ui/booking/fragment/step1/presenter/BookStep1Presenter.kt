package com.mif50.barberbooking.ui.booking.fragment.step1.presenter

import com.mif50.barberbooking.base.mvp.BasePresenter
import com.mif50.barberbooking.base.mvp.BaseView

interface BookStep1Presenter<T: BaseView>: BasePresenter<T> {
    fun loadAllSalon()
    fun loadBranches(cityName: String)
}