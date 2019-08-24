package com.mif50.barberbooking.app.helper

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class LayoutRes(val layout: Int = 0, val menu: Int = 0, val withBack: Boolean = false)