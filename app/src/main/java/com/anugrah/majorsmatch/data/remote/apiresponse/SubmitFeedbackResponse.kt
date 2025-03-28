package com.anugrah.majorsmatch.data.remote.apiresponse

data class SubmitFeedbackResponse(
	val code: Int,
	val data: Data,
	val message: String,
	val status: Boolean
)

data class Data(
	val testimoni: String,
	val id: Int,
	val fullname: String,
	val email: String
)

