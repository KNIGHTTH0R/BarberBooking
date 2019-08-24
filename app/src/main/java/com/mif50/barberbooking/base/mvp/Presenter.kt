package com.mif50.barberbooking.base.mvp

interface BaseView{
    fun onShowLoading()
    fun onHideLoading()
}

interface BasePresenter<T: BaseView>{
    var view: T ?

    fun onDestroy(){
        view = null
    }
}