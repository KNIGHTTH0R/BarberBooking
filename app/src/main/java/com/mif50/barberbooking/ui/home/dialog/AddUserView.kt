package com.mif50.barberbooking.ui.home.dialog

import com.mif50.barberbooking.base.mvp.BaseView

interface AddUserView : BaseView{
    fun onSuccessAddUser()
    fun onFailedAddUser(error: String)
}