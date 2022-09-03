package com.rafflypohan.myapplication.core.data

import com.rafflypohan.myapplication.core.data.api.ApiResponse
import com.rafflypohan.myapplication.core.data.api.ApiService
import com.rafflypohan.myapplication.core.data.responses.LoginResponse
import com.rafflypohan.myapplication.core.data.responses.RegisterResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun register(
        email: String,
        password: String,
        username: String
    ): Flow<ApiResponse<RegisterResponse>> = flow {
        try {
            val response = apiService.register(email, password, username)
            if (response.statusCode != 400) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty(response.message))
            }
        } catch (e: HttpException) {
            emit(ApiResponse.Error(e.message()))
        }
    }

    fun login(
        password: String,
        username: String
    ): Flow<ApiResponse<LoginResponse>> = flow {
        try {
            val response = apiService.login(password, username)
            if (response.statusCode != 401) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty(response.message))
            }
        } catch (e: HttpException) {
            emit(ApiResponse.Error(e.message()))
        }
    }
}