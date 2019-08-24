package com.mif50.barberbooking.ui.home.fragment.dashboard.adapter

import com.mif50.barberbooking.data.remote.model.Banner
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class HomeSliderAdapter (private val banners: List<Banner>) : SliderAdapter(){


    override fun getItemCount(): Int {
        return banners.count()
    }

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) {
        imageSlideViewHolder?.bindImageSlide(banners[position].image)
    }


}