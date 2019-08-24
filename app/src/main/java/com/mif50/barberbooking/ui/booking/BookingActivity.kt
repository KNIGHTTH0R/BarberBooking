package com.mif50.barberbooking.ui.booking


import android.os.Bundle
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.viewpager.widget.ViewPager
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.helper.LayoutRes
import com.mif50.barberbooking.app.ktx.toast
import com.mif50.barberbooking.base.BaseActivity
import com.mif50.barberbooking.data.local.DataObject
import com.mif50.barberbooking.data.remote.model.Barber
import com.mif50.barberbooking.ui.booking.adapter.ViewPagerAdapter
import com.mif50.barberbooking.ui.booking.fragment.step2.BookStep2Fragment
import com.mif50.barberbooking.ui.booking.presenter.BookingPresenterImpl
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_booking.*

@LayoutRes(layout = R.layout.activity_booking)
class BookingActivity : BaseActivity(), View.OnClickListener, BookingView {


    private val spots by lazy {
        SpotsDialog.Builder().setContext(this).setCancelable(false).build()
    }

    private val presenter by lazy {
        BookingPresenterImpl(this)
    }


    override fun bindView(savedInstanceState: Bundle?) {
        initStepView()
        setColorButton()
        initViewPager()
        setListener()

    }

    private fun initStepView() {
        val steps = arrayListOf("Salon ","Barber","Time","Confirm")
        stepView.setSteps(steps)
    }

    private fun setColorButton() {
        if (nextBtn.isEnabled) nextBtn.setBackgroundResource(R.color.colorBtn) else nextBtn.setBackgroundResource(R.color.colorDarkGray)
        if (previousBtn.isEnabled) previousBtn.setBackgroundResource(R.color.colorBtn) else previousBtn.setBackgroundResource(R.color.colorDarkGray)
    }

    private fun initViewPager() {
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.offscreenPageLimit = 4 // to save state of fragment
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                stepView.go(position,true)
                previousBtn.isEnabled = position != 0
                setColorButton()
            }

        })
    }

    private fun setListener() {
        previousBtn.setOnClickListener(this)
        nextBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            previousBtn.id -> whenPreviousBtnClicked()
            nextBtn.id ->  whenNextBtnClicked()

        }
    }
    private fun whenPreviousBtnClicked() {
        if (DataObject.indexViewPager == 3 || DataObject.indexViewPager > 0) {
            DataObject.indexViewPager --
            viewPager.currentItem = DataObject.indexViewPager
        }
    }

    private fun whenNextBtnClicked() {
        if (DataObject.indexViewPager<3|| DataObject.indexViewPager == 0) {

            when (DataObject.indexViewPager){
                0->  {
                    if (DataObject.currentSalon == null){
                        toast("You should select salon to go to next step")
                    } else {
                        DataObject.indexViewPager++
                        if (DataObject.indexViewPager == 1){
                            presenter.loadBarbers(DataObject.currentSalon!!.salonId)
                        }
                        viewPager.currentItem = DataObject.indexViewPager
                    }
                }
                1 -> {
                    if (DataObject.currentBarber == null){
                        toast("You should select barber to go to next step")
                    } else {
                        DataObject.indexViewPager++
                        if (DataObject.indexViewPager == 2){
                            // go to time

                        }
                        viewPager.currentItem = DataObject.indexViewPager
                    }
                }
                2 -> {}
                else -> viewPager.currentItem = 0
            }

        }
    }


    override fun onShowLoading() {
        spots.show()
    }

    override fun onHideLoading() {
        spots.dismiss()
    }

    override fun onShowBarbers(barbers: ArrayList<Barber>) {
        // sent broad cast receiver
        LocalBroadcastManager.getInstance(this).sendBroadcast(BookStep2Fragment.newIntentBroadcast(barbers))
    }

    override fun onErrorBarber(messageError: String) {
        toast(messageError)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        DataObject.currentSalon = null
    }

}
