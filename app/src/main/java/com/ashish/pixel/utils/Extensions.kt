package com.ashish.pixel.utils

import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

fun ImageView.load(uri: String?) {
    Glide.with(context)
        .load(uri)
        .transition(withCrossFade())
        .into(this)
}

fun ImageView.loadWithThumbnail(uri: String?, sizeMultiplier: Float = 0.25f) {
    Glide.with(context)
        .load(uri)
        .thumbnail(sizeMultiplier)
        .transition(withCrossFade())
        .into(this)
}

/**
 * Display the Toast [message], with [Toast.LENGTH_SHORT] duration.
 */
fun Fragment.toast(@StringRes message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

/**
 * Display the Toast [message], with [Toast.LENGTH_SHORT] duration.
 */
fun Fragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

/**
 * Display the Toast [message], with [Toast.LENGTH_LONG] duration.
 */
fun Fragment.longToast(@StringRes message: Int) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

/**
 * Display the Toast [message], with [Toast.LENGTH_LONG] duration.
 */
fun Fragment.longToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}