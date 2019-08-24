package com.mif50.barberbooking.ui.home.presenter

import com.mif50.barberbooking.base.mvp.BasePresenter
import com.mif50.barberbooking.base.mvp.BaseView

interface HomePresenter<T: BaseView>: BasePresenter<T>{

    fun checkUserLoginWithAccountKit()


}