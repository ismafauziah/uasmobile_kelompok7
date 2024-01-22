package hr.tvz.android.musicsocial.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Music(
    val _id: String,
    val title: String,
    val fileName: String,
    val __v: Int
) : Parcelable {}