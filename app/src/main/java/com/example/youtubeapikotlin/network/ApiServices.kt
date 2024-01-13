package com.example.youtubeapikotlin.network
import com.example.youtubeapikotlin.model.ChannelModel
import com.example.youtubeapikotlin.model.VideoYtModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface ApiServices {

    @GET("channels")
    fun getChannel(
        @Query("part") part: String,
        @Query("id") id: String
    ) : Call<ChannelModel>

    @GET("search")
    fun getvideo(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("order") order: String,
    ) : Call<VideoYtModel>

}