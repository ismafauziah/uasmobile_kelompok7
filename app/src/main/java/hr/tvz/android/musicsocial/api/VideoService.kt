package hr.tvz.android.musicsocial.api

import hr.tvz.android.musicsocial.model.Video
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class VideoService {

    private val api = Retrofit.Builder()
        .baseUrl(Service.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(VideoAPI::class.java)

    fun getAllVideos(): Single<List<Video>> {
        return api.getVideos()
    }

}