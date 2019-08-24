package com.mif50.barberbooking.ui.booking.fragment

import android.os.Bundle
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.helper.LayoutRes
import com.mif50.barberbooking.base.BaseFragment

@LayoutRes(layout = R.layout.fragment_book_step_4)
class BookStep4Fragment: BaseFragment() {

    companion object{
        fun newInstance(): BookStep4Fragment = BookStep4Fragment().apply {  }
    }

    override fun bindView(savedInstanceState: Bundle?) {

    }
}