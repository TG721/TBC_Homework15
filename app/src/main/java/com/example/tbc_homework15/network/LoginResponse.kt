package com.example.tbc_homework15.network

data class LoginResponse(
    val s: String?
)
{
    data class LoginItself(
        val email:String?,
        val password:String?
    )
}