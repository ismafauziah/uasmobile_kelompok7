package com.example.alaproapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Snippet(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val liveBroadcastContent: String,
    val publishTime: String,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val title: String
): Parcelable