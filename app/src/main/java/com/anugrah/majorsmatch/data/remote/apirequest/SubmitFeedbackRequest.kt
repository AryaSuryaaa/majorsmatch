package com.anugrah.majorsmatch.data.remote.apirequest

import com.google.gson.annotations.SerializedName

data class SubmitFeedbackRequest(
  @SerializedName("fullname")
  val fullName: String,
  @SerializedName("email")
  val email: String,
  @SerializedName("testimoni")
  val testimony: String
)
