package com.anugrah.majorsmatch.ui.screen.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.domain.usecase.registerusecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
  private val registerUseCase: RegisterUseCase
): ViewModel() {
  private val _uiState = MutableStateFlow(RegisterUiState())
  val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

  fun setFullName(fullName: String) {
    _uiState.update { state ->
      state.copy(fullName = fullName)
    }
  }

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
    val fullNameError = Validator.validateFullName(_uiState.value.fullName)
    val usernameError = Validator.validateName(_uiState.value.username)
    val emailError = Validator.validateEmail(_uiState.value.email)
    val passwordError = Validator.validatePassword(_uiState.value.password)
    val confirmPasswordError = Validator.validateConfirmPassword(_uiState.value.password, _uiState.value.confirmPassword)

    _uiState.value = _uiState.value.copy(
      fullNameError = fullNameError,
      usernameError = usernameError,
      emailError = emailError,
      passwordError = passwordError,
      confirmPasswordError = confirmPasswordError
    )

    return fullNameError == null && usernameError == null && emailError == null && passwordError == null && confirmPasswordError == null
  }

  fun register() {
    val param = RegisterRequest(
      fullName = _uiState.value.fullName,
      username = _uiState.value.username,
      email = _uiState.value.email,
      password = _uiState.value.password,
      confirmPass = _uiState.value.confirmPassword
    )
    if (!validateForm()) return

    viewModelScope.launch {
      registerUseCase(param).collect { result ->
        _uiState.update {
          when(result) {
            is ResultState.Loading -> it.copy(registerResult = ResultState.Loading)
            is ResultState.Success -> it.copy(registerResult = ResultState.Success(result.data))
            is ResultState.Error -> it.copy(registerResult = ResultState.Error(result.error))
            else -> it
          }
        }
      }
    }
  }

}