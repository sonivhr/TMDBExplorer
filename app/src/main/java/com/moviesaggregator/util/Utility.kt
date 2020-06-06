package com.moviesaggregator.util

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.snackbar.Snackbar
import com.moviesaggregator.R
import java.text.SimpleDateFormat
import java.util.*

private const val URL_POSTER_IMAGE_PATH = "http://image.tmdb.org/t/p/w185/"
private const val GLIDE_IMAGE_CORNER_RADIUS = 10

fun String.convertDateToTMDBStyle() : String {
    val originalFormat = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
    val targetFormat = SimpleDateFormat("MMM d, yyyy")
    val date = originalFormat.parse(this)
    return targetFormat.format(date)
}

fun ImageView.loadImageWithGlide(urlPath: String) {
    Glide.with(this.context)
        .load(URL_POSTER_IMAGE_PATH + urlPath)
        .transform(RoundedCorners(GLIDE_IMAGE_CORNER_RADIUS))
        .into(this)
}

fun Activity.showSnackBar(message: String) {
    val snackbar = Snackbar.make(this.findViewById(R.id.mainScreenCoordinatorLayout),
        message, Snackbar.LENGTH_LONG)
    snackbar.show()
}