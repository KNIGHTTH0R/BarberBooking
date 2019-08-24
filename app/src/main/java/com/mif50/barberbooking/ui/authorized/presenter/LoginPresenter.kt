package com.mif50.barberbooking.ui.authorized.presenter

import com.mif50.barberbooking.base.mvp.BasePresenter
import com.mif50.barberbooking.base.mvp.BaseView

interface LoginPresenter <T: BaseView>: BasePresenter<T>{
    fun checkIfUserHasAccessToken()
}