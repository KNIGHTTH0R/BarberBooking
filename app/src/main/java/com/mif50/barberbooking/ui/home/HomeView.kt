package com.mif50.barberbooking.ui.home

import com.mif50.barberbooking.base.mvp.BaseView

interface HomeView: BaseView{

    fun showDashboard()
    fun showDialogToAddUser(phoneNumber: String)
    fun onErrorAccountKit(message: String?)

}