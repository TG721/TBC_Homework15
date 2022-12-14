package com.example.tbc_homework15.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_homework15.model.LoginResponse
import com.example.tbc_homework15.network.repository.Repository
import com.example.tbc_homework15.utils.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val repository: Repository) : ViewModel() {
    private val _loginResponseState = MutableStateFlow<ResponseState<LoginResponse>>(
        ResponseState.Empty()
    ) //mutable
    val loginResponseState = _loginResponseState.asStateFlow()

    fun logIn(email: String, password: String) {
        viewModelScope.launch {
            _loginResponseState.emit(ResponseState.Loading())
            val response: Response<LoginResponse> = repository.postLoginData(
                LoginResponse.LoginData(
                    email,
                    password
                )
            )
            try {
                val body: LoginResponse? = response.body()
                if (response.isSuccessful && response.body() != null) {
                    _loginResponseState.emit(ResponseState.Success(body!!))
                } else {
                    _loginResponseState.emit(ResponseState.Error(response.errorBody()?.string()))
                }
            } catch (e: Throwable) {
                _loginResponseState.emit(ResponseState.Error(message = e.message ?: "error"))
            }

        }
    }
}