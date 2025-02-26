package com.anugrah.majorsmatch.ui.screen.register

data class RegisterUiState(
  val username: String = "",
  val email: String = "",
  val password: String = "",
  val confirmPassword: String = "",
  val usernameError: String? = null,
  val emailError: String? = null,
  val passwordError: String? = null,
  val confirmPasswordError: String? = null
)