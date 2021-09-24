package com.example.simformdemo.retrofit

import com.example.simformdemo.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/api")
    fun getUserList(@Query("results") value : Int) : Call<User>

}