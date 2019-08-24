package com.mif50.barberbooking.ui.home.fragment.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mif50.barberbooking.R
import com.mif50.barberbooking.app.ktx.loadImage
import com.mif50.barberbooking.data.remote.model.Banner
import kotlinx.android.synthetic.main.row_look_book.view.*

class LookBookAdapter (private val lookBooks : List<Banner>): RecyclerView.Adapter<LookBookAdapter.LookBookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LookBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_look_book,parent,false)
        return LookBookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lookBooks.count()
    }

    override fun onBindViewHolder(holder: LookBookViewHolder, position: Int) {
        holder.itemView.apply {
            lookBookIv.loadImage(lookBooks[position].image)
        }
    }


    inner class LookBookViewHolder(v: View): RecyclerView.ViewHolder(v)
}