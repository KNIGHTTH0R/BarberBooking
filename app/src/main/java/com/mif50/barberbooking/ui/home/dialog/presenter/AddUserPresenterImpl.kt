package com.mif50.barberbooking.ui.home.dialog.presenter

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mif50.barberbooking.data.Config
import com.mif50.barberbooking.data.local.DataObject
import com.mif50.barberbooking.data.remote.model.User
import com.mif50.barberbooking.ui.home.dialog.AddUserView

class AddUserPresenterImpl(override var view: AddUserView?): AddUserPresenter<AddUserView>  {

    private val userRef : CollectionReference by lazy {
        FirebaseFirestore.getInstance().collection(Config.USER_REF)
    }

    override fun addUser(phoneNumber: String, name: String, address: String) {
        view?.onShowLoading()
        val user  = User(name,address,phoneNumber)
        userRef.document(phoneNumber)
            .set(user)
            .addOnCompleteListener {
                DataObject.currentUser = user
                view?.apply {
                    onHideLoading()
                    onSuccessAddUser()
                }


            }
            .addOnFailureListener {
                view?.apply {
                    onHideLoading()
                    onFailedAddUser(it.message ?: "failed when add new user to firestore")
                }

            }
    }

}