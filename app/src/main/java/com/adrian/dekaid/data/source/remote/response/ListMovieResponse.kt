package com.adrian.dekaid.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListMovieResponse(
    @field:SerializedName("result")
    val movies: ArrayList<MoviesResponse>
) : Parcelable