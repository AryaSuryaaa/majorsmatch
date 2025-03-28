package com.anugrah.majorsmatch.data.remote.apiresponse

import com.google.gson.annotations.SerializedName

data class GetQuestionResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val dataQuestions: List<QuestionItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class QuestionItem(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("sortNumber")
	val sortNumber: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("dominan")
	val dominan: String
)
