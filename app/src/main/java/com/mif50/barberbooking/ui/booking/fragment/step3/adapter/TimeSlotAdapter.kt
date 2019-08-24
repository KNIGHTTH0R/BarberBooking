package com.mif50.barberbooking.ui.booking.fragment.step3.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.ktx.changeTextColor
import com.mif50.barberbooking.app.ktx.getColorRes
import com.mif50.barberbooking.app.ktx.inflateLayout
import com.mif50.barberbooking.app.listener.OnTappedListener
import com.mif50.barberbooking.data.local.DataObject
import com.mif50.barberbooking.data.remote.model.TimeSlot
import kotlinx.android.synthetic.main.row_time_slot.view.*

class TimeSlotAdapter(private val timeSlots: ArrayList<TimeSlot>, private val listener: OnTappedListener):
    RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        return TimeSlotViewHolder(parent.inflateLayout(R.layout.row_time_slot))
    }

    override fun getItemCount(): Int {
       return DataObject.TIME_SLOT_COUNT
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        val timeSlot = timeSlots[position]


        holder.itemView.apply {
            timeSlotTv.text = StringBuilder(DataObject.convertTimeSlotToString(position))


            if (timeSlots.count() == 0) { // if all position is available , just show list
                availabilityTv.text = "Available"
                availabilityTv.changeTextColor(R.color.colorBlack)
                timeSlotTv.changeTextColor(R.color.colorBlack)
                timeSlotCardView.setBackgroundColor(context.getColorRes(R.color.colorWhite))
            } else {
                // if have position is blocked
                timeSlots.map {
                    val slot = it.slot.toInt()
                    if (slot == position) {
                        availabilityTv.text = "Blocked"
                        availabilityTv.changeTextColor(R.color.colorWhite)
                        timeSlotTv.changeTextColor(R.color.colorWhite)
                        timeSlotCardView.setBackgroundColor(context.getColorRes(R.color.colorDarkGray))
                    }
                }
            }




            if (timeSlot.isTapped) {
                timeSlotCardView.setBackgroundColor(context.getColorRes(R.color.colorOrangeDark))
            } else {
                timeSlotCardView.setBackgroundColor(context.getColorRes(R.color.colorWhite))
            }
            setOnClickListener { listener.onCellTapped(timeSlotCardView,position) }
        }

    }

    inner class TimeSlotViewHolder(v: View): RecyclerView.ViewHolder(v)
}