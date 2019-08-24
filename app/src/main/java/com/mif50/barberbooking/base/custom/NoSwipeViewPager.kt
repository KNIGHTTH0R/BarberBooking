package com.mif50.barberbooking.base.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager

class NoSwipeViewPager: ViewPager{

    constructor(context: Context): super(context) {
        setMyScroller()
    }
    constructor(context: Context,attr: AttributeSet): super(context,attr) {
        setMyScroller()
    }


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    private fun setMyScroller(){
        try {
            val viewPager: Class<*> = ViewPager::class.java
            val scroller = viewPager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, MyScroller(context))
        }catch (e: NoSuchFileException){
            e.printStackTrace()
        } catch (e: IllegalAccessException){
            e.printStackTrace()
        }
    }

    class MyScroller(context: Context?) : Scroller(context, DecelerateInterpolator()) {

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, 350)
        }

    }

}