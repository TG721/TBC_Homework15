package com.example.tbc_homework15.network

import com.example.tbc_homework15.model.LoginResponse
import com.example.tbc_homework15.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("login")
    suspend fun logUser(@Body user: LoginResponse.LoginData): Response<LoginResponse>

    @POST("register")
    suspend fun registerUser(@Body user: RegisterResponse.RegisterData): Response<RegisterResponse>

}