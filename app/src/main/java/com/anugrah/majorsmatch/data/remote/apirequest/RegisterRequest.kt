package com.anugrah.majorsmatch.data.remote.apirequest

data class RegisterRequest (
  val fullName: String,
  val email: String,
  val username: String,
  val password: String,
  val confirmPass: String
)