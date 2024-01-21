package hr.tvz.android.musicsocial.api

import hr.tvz.android.musicsocial.model.User
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {

    @FormUrlEncoded
    @POST("users/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ) : Single<User>

    @FormUrlEncoded
    @POST("users/register")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("image") image: String
    ) : Single<User>

}