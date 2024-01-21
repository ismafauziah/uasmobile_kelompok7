package hr.tvz.android.musicsocial.api

import hr.tvz.android.musicsocial.model.Music
import io.reactivex.Single
import retrofit2.http.GET

interface MusicAPI {

    @GET("songs")
    fun getMusic(): Single<List<Music>>

}