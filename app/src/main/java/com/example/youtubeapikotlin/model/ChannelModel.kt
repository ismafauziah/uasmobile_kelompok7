package com.example.youtubeapikotlin.model

import androidx.annotation.Keep
import com.example.youtubeapikotlin.model.SnippetYt
import com.google.gson.annotations.SerializedName

@Keep
data class ChannelModel (

    @SerializedName("items")
    val items: List<Items>

){
    data class Items(
        @SerializedName("snippet")
        val snippet: SnippetYt
    )
}

