package hr.tvz.android.musicsocial.api

import hr.tvz.android.musicsocial.model.News
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsService {

    private val api = Retrofit.Builder()
        .baseUrl(Service.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NewsAPI::class.java)

    fun getAllNews(): Single<List<News>> {
        return api.getNews()
    }

    fun addNewNews(title: String, description: String, image: String, user: String): Single<News> {
        return api.addNews(title, description, image, user)
    }

}