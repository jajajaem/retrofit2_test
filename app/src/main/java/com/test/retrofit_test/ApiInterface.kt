package com.test.retrofit_test

import com.test.retrofit_test.retrofit2.userInfo
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface ApiInterface {
    @GET("users/")
    fun getUserData(
        @Query("id")id: String
    ): Call<userInfo>
}