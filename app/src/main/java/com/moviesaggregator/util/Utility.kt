package com.moviesaggregator.util

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.snackbar.Snackbar
import com.moviesaggregator.R
import com.moviesaggregator.api.apiresponseobjects.Genre
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

const val ARGUMENT_EXTRA_CONTENT_ID = "ARGUMENT_EXTRA_CONTENT_ID"
private const val URL_ORIGINAL_IMAGE_PATH = "https://image.tmdb.org/t/p/original/"
private const val URL_POSTER_IMAGE_PATH = "http://image.tmdb.org/t/p/w185/"
private const val GLIDE_IMAGE_CORNER_RADIUS = 10
const val EMPTY_STRING = ""

fun String?.convertDateToTMDBStyle() : String {
    if (this.isNullOrEmpty()) {
        return EMPTY_STRING
    }
    val originalFormat = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
    val targetFormat = SimpleDateFormat("MMM d, yyyy")
    val date = originalFormat.parse(this)
    return targetFormat.format(date)
}

fun Int?.getFormattedContentRunTime(): String {

    if (this == null || this == 0) {
        return EMPTY_STRING
    }

    val hour: Int = this / 60
    val minutes: Int = this % 60
    if (hour > 0 && minutes > 0) {
        return "Running time: ${hour}h ${minutes}m"
    } else if (hour > 0 && minutes == 0) {
        return "Running time: $hour hours"
    } else if (hour == 0 && minutes > 0) {
        return "Running time: $minutes minutes"
    }
    return "Running time: ${hour}h ${minutes}m"
}

fun ImageView.loadPosterImageWithGlide(urlPath: String) {
    Glide.with(this.context)
        .load(URL_POSTER_IMAGE_PATH + urlPath)
        .transform(RoundedCorners(GLIDE_IMAGE_CORNER_RADIUS))
        .into(this)
}

fun ImageView.loadOriginalImageWithGlide(urlPath: String) {
    Glide.with(this.context)
        .load(URL_ORIGINAL_IMAGE_PATH + urlPath)
        .into(this)
}

fun Activity.showSnackBar(message: String) {
    val snackbar = Snackbar.make(this.findViewById(R.id.mainScreenCoordinatorLayout),
        message, Snackbar.LENGTH_LONG)
    snackbar.show()
}

fun List<Genre>?.getFormattedGenres() : String {
    if (this.isNullOrEmpty()) {
        return EMPTY_STRING
    }
    val stringBuilder = StringBuilder()
    stringBuilder.append("Genres: ")
    stringBuilder.append(this[0].name)
    for (i in 1 until this.size) {
        stringBuilder.append(", ${this[i].name}")
    }
    return stringBuilder.toString()
}