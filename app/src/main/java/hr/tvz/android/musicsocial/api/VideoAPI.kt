package hr.tvz.android.musicsocial.api

import hr.tvz.android.musicsocial.model.Video
import io.reactivex.Single
import retrofit2.http.GET

interface VideoAPI {

    @GET("videos")
    fun getVideos(): Single<List<Video>>

}