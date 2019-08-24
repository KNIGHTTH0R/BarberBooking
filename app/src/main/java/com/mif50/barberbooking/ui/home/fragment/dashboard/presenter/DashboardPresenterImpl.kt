package com.mif50.barberbooking.ui.home.fragment.dashboard.presenter

import com.facebook.accountkit.AccountKit
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mif50.barberbooking.data.Config
import com.mif50.barberbooking.data.local.DataObject
import com.mif50.barberbooking.data.remote.model.Banner
import com.mif50.barberbooking.ui.home.fragment.dashboard.DashboardView

class DashboardPresenterImpl (override var view: DashboardView?): DashboardPresenter<DashboardView>{

    private val fireInstance = FirebaseFirestore.getInstance()

    private val bannerRef: CollectionReference by lazy {
        fireInstance.collection(Config.BANNER_REF)
    }

    private val lookBookRef: CollectionReference by lazy {
        fireInstance.collection(Config.LOOK_BOOK_REF)
    }

    override fun loadDashboardData() {

        AccountKit.getCurrentAccessToken()?.let {
            view?.updateUI(DataObject.currentUser?.name ?: "name not found")
        }
        loadBanner()
        loadLookBook()
    }

    private fun loadBanner(){
        bannerRef.get()
            .addOnSuccessListener {
                if (it == null) return@addOnSuccessListener
                val banners = arrayListOf<Banner>()
                it.map {bannerSnapShot ->
                    val banner = bannerSnapShot.toObject(Banner::class.java)
                    banners.add(banner)
                }
                view?.onShowBanners(banners)
            }
            .addOnFailureListener {
                view?.onErrorBanner(it.localizedMessage)
            }
    }

    private fun loadLookBook(){
        lookBookRef.get()
            .addOnSuccessListener {
                if (it == null) return@addOnSuccessListener
                val lookBooks = arrayListOf<Banner>()
                it.map { lookBookSnapShot ->
                    val lookBook = lookBookSnapShot.toObject(Banner::class.java)
                    lookBooks.add(lookBook)
                }
                view?.onShowLookBooks(lookBooks)
            }
            .addOnFailureListener {
                view?.onErrorLookBooks(it.localizedMessage)
            }
    }

}