package com.rafflypohan.myapplication.ui.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafflypohan.myapplication.core.data.RemoteDataSource
import com.rafflypohan.myapplication.core.data.api.ApiResponse
import com.rafflypohan.myapplication.core.data.responses.LoginResponse
import com.rafflypohan.myapplication.core.data.responses.RegisterResponse
import com.rafflypohan.myapplication.ui.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    ViewModel() {

    private val _register = MutableStateFlow<Resource<RegisterResponse>>(Resource.Loading(null))
    val register = _register.asStateFlow()

    fun registerUser(
        email: String,
        password: String,
        username: String
    ) = viewModelScope.launch {
        remoteDataSource.register(email, password, username).collect {
            when(it){
                is ApiResponse.Success -> {
                    _register.value = Resource.Success(it.data)
                }
                is ApiResponse.Empty -> {
                    _register.value = Resource.Error(it.errorMessage)
                }
                is ApiResponse.Error -> {
                    _register.value = Resource.Error(it.errorMessage)
                }
            }
        }
    }

    private val _login = MutableStateFlow<Resource<LoginResponse>>(Resource.Loading(null))
    val login = _login.asStateFlow()

    fun loginUser(
        password: String,
        username: String
    ) = viewModelScope.launch {
        remoteDataSource.login(password, username).collect {
            when(it){
                is ApiResponse.Success -> {
                    _login.value = Resource.Success(it.data)
                }
                is ApiResponse.Empty -> {
                    _login.value = Resource.Error(it.errorMessage)
                }
                is ApiResponse.Error -> {
                    _login.value = Resource.Error(it.errorMessage)
                }
            }
        }
    }
}
