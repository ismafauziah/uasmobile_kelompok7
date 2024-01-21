package hr.tvz.android.musicsocial.api

import hr.tvz.android.musicsocial.model.News
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NewsAPI {

    @GET("news")
    fun getNews(): Single<List<News>>

    @FormUrlEncoded
    @POST("news")
    fun addNews(
        @Field("title") title: String,
        @Field("description") description: String,
        @Field("image") image: String,
        @Field("user") user: String
    ) : Single<News>

}