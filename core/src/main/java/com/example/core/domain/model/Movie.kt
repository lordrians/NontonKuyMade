package com.example.core.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    var id: Int? = null,
    var overview: String? = null,
    var originalTitle: String? = null,
    var genres: String? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null,
    var releaseDate: String? = null,
    var runtime: Int? = null,
    var budget: Int? = null,
    var revenue: Int? = null,
    var voteAverage: Double? = null,
    var isFav: Boolean = false
): Parcelable