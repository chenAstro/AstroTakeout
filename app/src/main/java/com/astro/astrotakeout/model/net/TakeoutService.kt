package com.astro.astrotakeout.model.net

import retrofit2.Call
import retrofit2.http.GET


interface TakeoutService {

//    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String): Call<List<Repo>>

    @GET("home")
    fun getHomeInfoData(): Call<ResponseInfo>
}