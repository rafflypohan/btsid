package com.rafflypohan.myapplication.core.data.api

import com.rafflypohan.myapplication.core.data.responses.LoginResponse
import com.rafflypohan.myapplication.core.data.responses.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("username") username: String,
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("password") password: String,
        @Field("username") username: String,
    ): LoginResponse
}