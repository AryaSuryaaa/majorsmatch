package com.anugrah.majorsmatch.data.remote.apiresponse

import com.google.gson.annotations.SerializedName

data class GetTestimonyResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val dataTestimony: List<DataTestimony>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class DataTestimony(

	@field:SerializedName("testimoni")
	val testimony: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("fullname")
	val fullName: String,

	@field:SerializedName("email")
	val email: String
)
