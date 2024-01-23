package com.example.alaproapp.network

import com.example.alaproapp.model.YoutubeModel
import retrofit2.*
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    @GET("search")
    fun getAllData(
        @Query("key") key: String = "AIzaSyDd3sQ5i0GR6K2pnpxEMXO7ujrwfwOa1rI",
        @Query("channelId") channelId: String = "UCkXmLjEr95LVtGuIm3l2dPg",
        @Query("order") order: String = "date",
        @Query("maxResults") maxResult: String = "1800",
        @Query("part") part: String = "snippet,id"
    ): Call<YoutubeModel>
}