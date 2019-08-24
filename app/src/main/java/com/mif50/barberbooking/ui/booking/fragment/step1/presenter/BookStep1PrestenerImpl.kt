package com.mif50.barberbooking.ui.booking.fragment.step1.presenter

import com.google.firebase.firestore.FirebaseFirestore
import com.mif50.barberbooking.data.Config
import com.mif50.barberbooking.data.local.DataObject
import com.mif50.barberbooking.data.remote.model.Salon
import com.mif50.barberbooking.ui.booking.fragment.step1.BookStep1View

class BookStep1PrestenerImpl (override var view: BookStep1View?): BookStep1Presenter<BookStep1View> {

    private val fireInstance = FirebaseFirestore.getInstance()


    private val salonRef = fireInstance.collection(Config.ALL_SALON_REF)
    private val branchRef = fireInstance.collection(Config.ALL_SALON_REF)

    override fun loadAllSalon() {
        salonRef.get()
            .addOnSuccessListener {
                if (it == null) return@addOnSuccessListener
                val salons = arrayListOf("choose your city")
                it.map {salonSnapshot ->
                    salons.add(salonSnapshot.id)
                }
                view?.onShowSalons(salons)
            }
            .addOnFailureListener {
                view?.onErrorSalons(it.message ?: "error when get all salon")
            }
    }

    override fun loadBranches(cityName: String) {
        DataObject.city = cityName
        view?.onShowLoading()
        branchRef.document(cityName)
            .collection(Config.BRANCH_REF)
            .get()
            .addOnSuccessListener {
                view?.onHideLoading()
                if (it == null) return@addOnSuccessListener
                val branches = ArrayList<Salon>()
                it.map {branchSnapshot->
                    val salon = branchSnapshot.toObject(Salon::class.java)
                    salon.salonId = branchSnapshot.id
                    branches.add(salon)
                }
                view?.onShowBranches(branches)
            }
            .addOnFailureListener {
                view?.apply {
                    onHideLoading()
                    onErrorBranches(it.localizedMessage)
                }
            }
    }

}