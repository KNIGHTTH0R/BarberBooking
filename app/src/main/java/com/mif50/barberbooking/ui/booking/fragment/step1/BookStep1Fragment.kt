package com.mif50.barberbooking.ui.booking.fragment.step1

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.helper.LayoutRes
import com.mif50.barberbooking.app.helper.rv.SpacesItemDecoration
import com.mif50.barberbooking.app.ktx.setGone
import com.mif50.barberbooking.app.ktx.setVisiable
import com.mif50.barberbooking.app.ktx.toast
import com.mif50.barberbooking.app.listener.OnTappedListener
import com.mif50.barberbooking.base.BaseFragment
import com.mif50.barberbooking.data.local.DataObject
import com.mif50.barberbooking.data.remote.model.Salon
import com.mif50.barberbooking.ui.booking.fragment.step1.adapter.BranchAdapter
import com.mif50.barberbooking.ui.booking.fragment.step1.presenter.BookStep1Presenter
import com.mif50.barberbooking.ui.booking.fragment.step1.presenter.BookStep1PrestenerImpl
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_book_step_1.*

@LayoutRes(layout = R.layout.fragment_book_step_1)
class BookStep1Fragment: BaseFragment(), BookStep1View ,OnTappedListener{



    private lateinit var presenter: BookStep1Presenter<BookStep1View>
    private val spots by lazy {
        SpotsDialog.Builder().setContext(this.context).setCancelable(false).build()
    }

    private var salons = ArrayList<Salon>()

    companion object{
        fun newInstance(): BookStep1Fragment = BookStep1Fragment().apply {  }
    }

    override fun bindView(savedInstanceState: Bundle?) {
        initAdapter()
        presenter = BookStep1PrestenerImpl(this)
        presenter.loadAllSalon()
    }

    private fun initAdapter() {
        salonRv.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context,2)
            addItemDecoration(SpacesItemDecoration(4))

        }
    }

    override fun onShowSalons(salons: List<String>) {
        spinner.setItems(salons)
        spinner.setOnItemSelectedListener { view, position, id, item ->
            if (position > 0) presenter.loadBranches(item.toString())
            else salonRv.setGone()
        }
    }

    override fun onErrorSalons(messageError: String) {
        activity?.toast(messageError)
    }

    override fun onShowLoading() {
        spots.show()
    }

    override fun onHideLoading() {
        spots.dismiss()
    }


    override fun onShowBranches(branches: ArrayList<Salon>) {
        salonRv.setVisiable()
        this.salons = branches
        salonRv.adapter = BranchAdapter(branches = salons,listener = this)
    }

    override fun onErrorBranches(messageError: String) {
        activity?.toast(messageError)
    }


    override fun onCellTapped(v: View, position: Int) {
        val tappedSalon = salons[position]
        if (tappedSalon.isTapped) return
        // set other to false
        salons.map {
            it.isTapped = false
        }

        // set tapped to true

        tappedSalon.isTapped = true
        DataObject.currentSalon = tappedSalon
        val adapter = salonRv?.adapter as? BranchAdapter
        adapter?.notifyDataSetChanged()

    }

}