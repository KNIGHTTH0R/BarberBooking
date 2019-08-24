package com.mif50.barberbooking.app.listener

import android.view.View

interface OnTappedListener {
    fun onCellTapped(v: View, position: Int)
}