package hr.tvz.android.musicsocial.api

import hr.tvz.android.musicsocial.model.Music
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MusicService {

    private val api = Retrofit.Builder()
        .baseUrl(Service.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MusicAPI::class.java)

    fun getAllMusic(): Single<List<Music>> {
        return api.getMusic()
    }

}