package com.mif50.barberbooking.ui.home.presenter

import com.facebook.accountkit.Account
import com.facebook.accountkit.AccountKit
import com.facebook.accountkit.AccountKitCallback
import com.facebook.accountkit.AccountKitError
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mif50.barberbooking.data.Config
import com.mif50.barberbooking.data.local.DataObject
import com.mif50.barberbooking.data.remote.model.User
import com.mif50.barberbooking.ui.home.HomeView

class HomePresenterImpl (override var view: HomeView?): HomePresenter<HomeView> {

    private val userRef : CollectionReference by lazy {
        FirebaseFirestore.getInstance().collection(Config.USER_REF)
    }


    override fun checkUserLoginWithAccountKit() {
        view?.onShowLoading()
        AccountKit.getCurrentAccount(object : AccountKitCallback<Account> {
            override fun onSuccess(account: Account?) {
                account?.let {
                    val currentUser = userRef.document(account.phoneNumber.toString())
                    currentUser.get()
                        .addOnCompleteListener {
                            if (it.isSuccessful){
                                val userSnapShot = it.result ?: return@addOnCompleteListener
                                if (!userSnapShot.exists()){
                                    view?.apply {
                                        onHideLoading()
                                        showDialogToAddUser(account.phoneNumber.toString())
                                    }

                                }else{
                                    DataObject.currentUser = userSnapShot.toObject(User::class.java)
                                    view?.apply {
                                        onHideLoading()
                                        showDashboard()
                                    }
                                }
                            }
                        }
                }
            }

            override fun onError(error: AccountKitError?) {
                view?.onHideLoading()
                view?.onErrorAccountKit(error?.errorType?.message)
            }

        })
    }



}