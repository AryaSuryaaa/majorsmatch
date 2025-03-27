package com.anugrah.majorsmatch.data.remote.apiresponse

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val dataLogin: DataLogin,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataLogin(

	@field:SerializedName("user")
	val dataUser: DataUser,

	@field:SerializedName("token")
	val token: String
)

data class DataUser(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("fullname")
	val fullName: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
