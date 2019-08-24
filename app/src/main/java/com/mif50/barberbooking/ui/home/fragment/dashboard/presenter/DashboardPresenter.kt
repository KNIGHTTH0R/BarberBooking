package com.mif50.barberbooking.ui.home.fragment.dashboard.presenter

import com.mif50.barberbooking.base.mvp.BasePresenter
import com.mif50.barberbooking.base.mvp.BaseView

interface DashboardPresenter <T: BaseView>: BasePresenter<T> {
    fun loadDashboardData()
}