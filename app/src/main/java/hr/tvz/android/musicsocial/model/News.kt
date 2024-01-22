package hr.tvz.android.musicsocial.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val _id: String,
    val title: String,
    val description: String,
    val image: String,
    val user: String,
    val time: String,
    val __v: Int
) : Parcelable {}