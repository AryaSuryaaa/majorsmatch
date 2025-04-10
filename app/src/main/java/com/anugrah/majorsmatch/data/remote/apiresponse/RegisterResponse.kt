package com.anugrah.majorsmatch.data.remote.apiresponse

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("dataRegister")
	val dataRegister: DataRegister,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataRegister(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("fullname")
	val fullName: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
