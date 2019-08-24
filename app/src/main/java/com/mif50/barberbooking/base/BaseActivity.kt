package com.mif50.barberbooking.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mif50.barberbooking.app.ktx.getLayoutRes
import com.mif50.barberbooking.base.mvp.BaseView

abstract class BaseActivity: AppCompatActivity(), BaseView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes().layout)
        bindView(savedInstanceState)
    }

    abstract fun bindView(savedInstanceState: Bundle?)

    override fun onShowLoading() {

    }

    override fun onHideLoading() {

    }
}