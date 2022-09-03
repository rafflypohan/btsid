package com.rafflypohan.myapplication.core.data.responses

import com.squareup.moshi.Json

data class RegisterResponse(

	@Json(name="errorMessage")
	val errorMessage: String? = null,

	@Json(name="message")
	val message: String,

	@Json(name="statusCode")
	val statusCode: Int
)
