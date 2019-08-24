package com.mif50.barberbooking.ui.booking.fragment.step1.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.ktx.getColorRes
import com.mif50.barberbooking.app.ktx.inflateLayout
import com.mif50.barberbooking.app.listener.OnTappedListener
import com.mif50.barberbooking.data.remote.model.Salon
import kotlinx.android.synthetic.main.row_branch.view.*

class BranchAdapter(private val branches: List<Salon>, private val listener: OnTappedListener) :
    RecyclerView.Adapter<BranchAdapter.BranchAdapterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BranchAdapterViewHolder {
        return BranchAdapterViewHolder(parent.inflateLayout(R.layout.row_branch))
    }

    override fun getItemCount(): Int {
        return branches.count()
    }

    override fun onBindViewHolder(holder: BranchAdapterViewHolder, position: Int) {
        val branch = branches[position]
        holder.itemView.apply {
            branchNameTv.text = branch.name
            branchAddressTv.text = branch.address
            if (branch.isTapped) {
                rowBranchCardView.setBackgroundColor(context.getColorRes(R.color.colorOrangeDark))
            } else {
                rowBranchCardView.setBackgroundColor(context.getColorRes(R.color.colorWhite))
            }
            setOnClickListener { listener.onCellTapped(rowBranchCardView, position) }

        }
    }


    inner class BranchAdapterViewHolder(v: View) : RecyclerView.ViewHolder(v)
}