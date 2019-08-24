package com.mif50.barberbooking.ui.home.fragment.dashboard


import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mif50.barberbooking.R
import com.mif50.barberbooking.ui.home.fragment.dashboard.adapter.HomeSliderAdapter
import com.mif50.barberbooking.app.helper.LayoutRes
import com.mif50.barberbooking.app.ktx.setVisiable
import com.mif50.barberbooking.app.ktx.startActivity
import com.mif50.barberbooking.app.ktx.toast
import com.mif50.barberbooking.app.service.PicassoImageLoadingService
import com.mif50.barberbooking.base.BaseFragment
import com.mif50.barberbooking.data.remote.model.Banner
import com.mif50.barberbooking.ui.booking.BookingActivity
import com.mif50.barberbooking.ui.home.fragment.dashboard.adapter.LookBookAdapter
import com.mif50.barberbooking.ui.home.fragment.dashboard.presenter.DashboardPresenter
import com.mif50.barberbooking.ui.home.fragment.dashboard.presenter.DashboardPresenterImpl
import kotlinx.android.synthetic.main.fragment_home.*
import ss.com.bannerslider.Slider


@LayoutRes(layout = R.layout.fragment_home)
class DashboardFragment : BaseFragment(), DashboardView, View.OnClickListener {

    private val presenter: DashboardPresenter<DashboardView> by lazy {
        DashboardPresenterImpl(this)
    }

    override fun bindView(savedInstanceState: Bundle?) {
        Slider.init(PicassoImageLoadingService())

        // check is logged ?
       presenter.loadDashboardData()
        setListener()
    }


    override fun updateUI(userName: String) {
        layoutUserInformation.setVisiable()
        usernameTv.text = userName
    }

    override fun onShowBanners(banners: List<Banner>) {
        bannerSlider.setAdapter(HomeSliderAdapter(banners))
    }

    override fun onShowLookBooks(lookBooks: List<Banner>) {
        lookBookRv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DashboardFragment.context)
            adapter =LookBookAdapter(lookBooks)
        }
    }

    override fun onErrorBanner(messageError: String) {
        activity?.toast(messageError)
    }

    override fun onErrorLookBooks(messageError: String) {
        activity?.toast(messageError)
    }

    private fun setListener() {
        bookingCardView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            bookingCardView.id -> activity?.startActivity<BookingActivity>()
        }
    }







}
