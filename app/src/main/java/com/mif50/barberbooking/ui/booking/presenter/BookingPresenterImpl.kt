package com.mif50.barberbooking.ui.booking.presenter

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mif50.barberbooking.data.Config
import com.mif50.barberbooking.data.local.DataObject
import com.mif50.barberbooking.data.remote.model.Barber
import com.mif50.barberbooking.ui.booking.BookingView

class BookingPresenterImpl(override var view: BookingView?): BookingPresenter<BookingView> {

    override fun loadBarbers(salonId: String) {
        if (DataObject.city.isNotEmpty()) {
            view?.onShowLoading()
            val barberRef = FirebaseFirestore.getInstance()
                .collection(Config.ALL_SALON_REF)
                .document(DataObject.city)
                .collection(Config.BRANCH_REF)
                .document(salonId)
                .collection(Config.BARBER_REF)
            barberRef.get()
                .addOnSuccessListener {
                    view?.onHideLoading()
                    val barbers = arrayListOf<Barber>()
                    it.map { barberSnapshot ->
                        val barber = barberSnapshot.toObject(Barber::class.java)
                        barber.password = "" // reset password because we in client app
                        barber.BarberId = barberSnapshot.id
                        barbers.add(barber)
                    }
                    Log.d("Barbers",barbers.toString())
                    view?.onShowBarbers(barbers)

                }
                .addOnFailureListener {
                    view?.apply {
                        onHideLoading()
                        onErrorBarber(it.localizedMessage)
                    }
                }
        }

    }

}