package com.mif50.barberbooking.ui.booking.fragment.step2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.GridLayoutManager
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.helper.LayoutRes
import com.mif50.barberbooking.app.helper.rv.SpacesItemDecoration
import com.mif50.barberbooking.app.listener.OnTappedListener
import com.mif50.barberbooking.base.BaseFragment
import com.mif50.barberbooking.data.local.DataObject
import com.mif50.barberbooking.data.remote.model.Barber
import com.mif50.barberbooking.ui.booking.fragment.step2.adapter.BarberAdapter
import kotlinx.android.synthetic.main.fragment_book_step_2.*

@LayoutRes(layout = R.layout.fragment_book_step_2)
class BookStep2Fragment: BaseFragment(), OnTappedListener {

    private lateinit var barbers: ArrayList<Barber>

    private val localBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(this.context!!)
    }


    companion object{
        private const val ACTION_STEP_2 = "action_broadcast_step_2"
        private const val EXTRA_BARBERS = "extra_barber_key"

        fun newIntentBroadcast(barbers: ArrayList<Barber>): Intent = Intent(ACTION_STEP_2)
            .putParcelableArrayListExtra(EXTRA_BARBERS,barbers)

        fun newInstance(): BookStep2Fragment = BookStep2Fragment().apply {  }
    }

    private val barberReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            // here get barbers form intent
            barbers = intent?.getParcelableArrayListExtra(EXTRA_BARBERS) ?: return
            barberRv.adapter = BarberAdapter(barbers = barbers,listener = this@BookStep2Fragment)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBroadcast()
    }

    private fun registerBroadcast() {
       localBroadcastManager.registerReceiver(barberReceiver, IntentFilter(ACTION_STEP_2))
    }

    override fun bindView(savedInstanceState: Bundle?) {
        initAdapter()
    }

    private fun initAdapter() {
        barberRv.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,2)
            addItemDecoration(SpacesItemDecoration(4))
        }
    }

    override fun onCellTapped(v: View, position: Int) {
        val tappedBarber = barbers[position]
        if (tappedBarber.isTapped) return
        // first set all barber isTapped to false
        barbers.map {
            it.isTapped = false
        }
        // set tapped barber to true and reload adapter
        tappedBarber.isTapped = true
        DataObject.currentBarber = tappedBarber
        (barberRv.adapter as? BarberAdapter)?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        unregisterBroadcast()
        super.onDestroy()
    }

    private fun unregisterBroadcast() {
        localBroadcastManager.unregisterReceiver(barberReceiver)
    }
}