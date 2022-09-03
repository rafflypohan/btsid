package com.rafflypohan.myapplication.core.data.responses

import com.squareup.moshi.Json

data class LoginResponse(

	@Json(name="data")
	val data: ResponseData,

	@Json(name="errorMessage")
	val errorMessage: String? = null,

	@Json(name="message")
	val message: String,

	@Json(name="statusCode")
	val statusCode: Int
)

data class ResponseData(

	@Json(name="token")
	val token: String
)
