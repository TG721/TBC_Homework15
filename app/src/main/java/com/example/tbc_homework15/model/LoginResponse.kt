package com.example.tbc_homework15.model

data class LoginResponse(
    val s: String?
) {
    data class LoginData(
        val email: String?,
        val password: String?
    )
}