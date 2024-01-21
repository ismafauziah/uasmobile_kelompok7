package hr.tvz.android.musicsocial.api

import hr.tvz.android.musicsocial.model.User
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserService {

    private val api = Retrofit.Builder()
        .baseUrl(Service.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(AuthAPI::class.java)

    fun loginUser(username: String, password: String): Single<User> {
        return api.login(username, password)
    }

    fun registerUser(username: String, password: String, image: String): Single<User> {
        return api.register(username, password, image)
    }

}