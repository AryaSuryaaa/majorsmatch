package com.anugrah.majorsmatch.ui.screen.register

import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse

data class RegisterUiState(
  val fullName: String = "",
  val username: String = "",
  val email: String = "",
  val password: String = "",
  val confirmPassword: String = "",
  val fullNameError: String? = null,
  val usernameError: String? = null,
  val emailError: String? = null,
  val passwordError: String? = null,
  val confirmPasswordError: String? = null,
  val registerResult: ResultState<RegisterResponse> = ResultState .Idle
)