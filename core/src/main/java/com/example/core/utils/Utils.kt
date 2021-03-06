package com.example.core.utils

import android.content.Context
import android.view.View


fun setGridPixel(mContext: Context): Int {
    val displayMetrics = mContext.resources.displayMetrics
    val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
    return (screenWidthDp / 180 + 0.5).toInt()
}

fun setVisible(v: View){
    v.visibility = View.VISIBLE
}

fun setInvisible(v: View){
    v.visibility = View.INVISIBLE
}

fun setGone(v: View){
    v.visibility = View.GONE
}