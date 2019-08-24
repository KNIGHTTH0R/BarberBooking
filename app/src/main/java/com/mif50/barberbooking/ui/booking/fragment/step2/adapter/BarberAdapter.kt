package com.mif50.barberbooking.ui.booking.fragment.step2.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.ktx.getColorRes
import com.mif50.barberbooking.app.ktx.inflateLayout
import com.mif50.barberbooking.app.listener.OnTappedListener
import com.mif50.barberbooking.data.remote.model.Barber
import kotlinx.android.synthetic.main.row_barber.view.*

class BarberAdapter(private val barbers: ArrayList<Barber>,private val listener: OnTappedListener): RecyclerView.Adapter<BarberAdapter.Companion.BarberViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BarberViewHolder {
        return BarberViewHolder(parent.inflateLayout(R.layout.row_barber))
    }

    override fun getItemCount(): Int {
       return barbers.count()
    }

    override fun onBindViewHolder(holder: BarberViewHolder, position: Int) {
        val barber = barbers[position]
        holder.itemView.apply {
            barberTv.text = barber.name
            barberRating.rating = barber.rating.toFloat()
            if (barber.isTapped) {
                barberCardView.setBackgroundColor(context.getColorRes(R.color.colorOrangeDark))
            } else {
                barberCardView.setBackgroundColor(context.getColorRes(R.color.colorWhite))
            }
            setOnClickListener { listener.onCellTapped(barberCardView,position) }
        }
    }


    companion object {
        class BarberViewHolder(v: View): RecyclerView.ViewHolder(v)
    }
}