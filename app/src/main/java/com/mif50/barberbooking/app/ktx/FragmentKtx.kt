package com.mif50.barberbooking.app.ktx

import androidx.fragment.app.Fragment
import com.mif50.barberbooking.app.helper.LayoutRes

fun Fragment.getLayoutRes(): LayoutRes {
    val annotation = this::class.java.annotations.find { it is LayoutRes } as? LayoutRes
    if (annotation != null) {
        return annotation
    } else {
        throw KotlinNullPointerException("Please add the LayoutRes annotation at the to of the class")
    }
}