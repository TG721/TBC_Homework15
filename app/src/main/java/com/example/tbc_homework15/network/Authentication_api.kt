package com.example.tbc_homework15.network

import com.example.tbc_homework15.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Authentication_api  {
    @POST("login")
    suspend fun logUser(@Body user: User): Response<LoginResponse>

    @POST("register")
    suspend fun registerUser(@Body user: User): Response<RegisterResponse>

}