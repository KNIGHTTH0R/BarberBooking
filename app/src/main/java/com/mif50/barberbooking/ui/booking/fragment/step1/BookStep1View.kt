package com.mif50.barberbooking.ui.booking.fragment.step1

import com.mif50.barberbooking.base.mvp.BaseView
import com.mif50.barberbooking.data.remote.model.Salon

interface BookStep1View: BaseView {
    fun onShowSalons(salons: List<String>)
    fun onErrorSalons(messageError: String)

    fun onShowBranches(branches: ArrayList<Salon>)
    fun onErrorBranches(messageError: String)
}