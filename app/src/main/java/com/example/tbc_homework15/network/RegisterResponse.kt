package com.example.tbc_homework15.network

data class RegisterResponse(
    val id: Int,
    val s: String?
)
{
    data class RegisterItself(
        val email:String?,
        val password:String?,
    )
}