package com.test.retrofit_test.retrofit2

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface ApiInterface {
    @GET("users/")
    fun getUserData(
        @Query("page")page : Int
    ): Call<List<userInfoItem>>
}