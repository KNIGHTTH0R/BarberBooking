package com.mif50.barberbooking.ui.home.fragment.dashboard

import com.mif50.barberbooking.base.mvp.BaseView
import com.mif50.barberbooking.data.remote.model.Banner

interface DashboardView: BaseView {
    fun updateUI(userName: String)

    fun onShowBanners(banners: List<Banner>)
    fun onShowLookBooks(lookBooks: List<Banner>)

    fun onErrorBanner(messageError: String)
    fun onErrorLookBooks(messageError: String)
}