package com.anugrah.majorsmatch.ui.screen.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

): ViewModel() {
  private val _uiState = MutableStateFlow(RegisterUiState())
  val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

  fun setUsername(username: String) {
    _uiState.value = _uiState.value.copy(username = username)

  }

  fun setEmail(email: String) {
    _uiState.value = _uiState.value.copy(email = email)
  }

  fun setPassword(password: String) {
    _uiState.value = _uiState.value.copy(password = password)
  }

  fun setConfirmPassword(confirmPassword: String) {
    _uiState.value = _uiState.value.copy(confirmPassword = confirmPassword)
  }

  private fun validateForm(): Boolean {
    val usernameError = Validator.validateName(_uiState.value.username)
    val emailError = Validator.validateEmail(_uiState.value.email)
    val passwordError = Validator.validatePassword(_uiState.value.password)
    val confirmPasswordError = Validator.validateConfirmPassword(_uiState.value.password, _uiState.value.confirmPassword)

    _uiState.value = _uiState.value.copy(
      usernameError = usernameError,
      emailError = emailError,
      passwordError = passwordError,
      confirmPasswordError = confirmPasswordError
    )

    return usernameError == null && emailError == null && passwordError == null && confirmPasswordError == null
  }

  fun register() {
    if (!validateForm()) return
  }

}