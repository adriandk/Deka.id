package com.adrian.dekaid.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "show")
data class ShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "showId")
    var showId: Int,

    @ColumnInfo(name = "showName")
    var showName: String,

    @ColumnInfo(name = "fansVote")
    var showVote: Double,

    @ColumnInfo(name = "showDescription")
    var showDescription: String,

    @ColumnInfo(name = "showFirstAir")
    var showFirstAir: String,

    @ColumnInfo(name = "showImage")
    var showImage: String,

    @ColumnInfo(name = "showSeason")
    var showSeason: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)