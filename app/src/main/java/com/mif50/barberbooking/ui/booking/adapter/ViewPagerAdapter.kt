package com.mif50.barberbooking.ui.booking.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mif50.barberbooking.ui.booking.fragment.step1.BookStep1Fragment
import com.mif50.barberbooking.ui.booking.fragment.step2.BookStep2Fragment
import com.mif50.barberbooking.ui.booking.fragment.step3.BookStep3Fragment
import com.mif50.barberbooking.ui.booking.fragment.BookStep4Fragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return  when(position) {
            0 -> BookStep1Fragment.newInstance()
            1 -> BookStep2Fragment.newInstance()
            2 -> BookStep3Fragment.newInstance()
            3 -> BookStep4Fragment.newInstance()
            else -> BookStep1Fragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return 4
    }



}