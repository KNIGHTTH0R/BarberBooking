package com.mif50.barberbooking.ui.home.dialog.presenter

import com.mif50.barberbooking.base.mvp.BasePresenter
import com.mif50.barberbooking.base.mvp.BaseView

interface AddUserPresenter<T: BaseView> : BasePresenter<T>{
    fun addUser(phoneNumber: String, name: String, address: String)
}