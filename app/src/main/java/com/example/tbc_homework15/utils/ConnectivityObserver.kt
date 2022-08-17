package com.example.tbc_homework15.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observeInternet(): Flow<Status>

    enum class Status {
Available, Unavailable, Losing, Lost
    }
}