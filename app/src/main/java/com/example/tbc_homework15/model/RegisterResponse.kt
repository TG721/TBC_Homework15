package com.example.tbc_homework15.model

data class RegisterResponse(
    val id: Int,
    val s: String?
) {
    data class RegisterData(
        val email: String?,
        val password: String?,
    )
}