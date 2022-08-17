package com.example.tbc_homework15.network

import com.example.tbc_homework15.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

//    private const val BASE_URL = "https://reqres.in/api/"

    private val instance by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getAuthApi(): AuthenticationApi = instance.create(AuthenticationApi::class.java)

}