package com.mif50.barberbooking.ui.authorized.presenter

import com.facebook.accountkit.AccountKit
import com.mif50.barberbooking.ui.authorized.LoginView

class LoginPresenterImpl (override var view : LoginView?): LoginPresenter<LoginView>{

    override fun checkIfUserHasAccessToken() {
        if (AccountKit.getCurrentAccessToken() != null){
            view?.showHome()
        }
    }

}