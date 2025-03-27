package com.anugrah.majorsmatch.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.data.remote.apirequest.LoginRequest
import com.anugrah.majorsmatch.domain.usecase.loginusecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
  private val loginUseCase: LoginUseCase
): ViewModel() {
  private val _uiState = MutableStateFlow(LoginUiState())
  val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

  fun setEmail(email: String) {
    _uiState.value = _uiState.value.copy(email = email)
  }

  fun setPassword(password: String) {
    _uiState.value = _uiState.value.copy(password = password)
  }

  fun login() {
    val params = LoginRequest(
      email = _uiState.value.email,
      password = _uiState.value.password
    )
    loginUseCase(params)
    viewModelScope.launch {
      loginUseCase(params).collect { result ->
        _uiState.update { state ->
          when(result) {
            is ResultState.Loading -> state.copy(loginResult = ResultState.Loading)
            is ResultState.Success -> state.copy(loginResult = ResultState.Success(result.data))
            is ResultState.Error -> state.copy(loginResult = ResultState.Error(result.error))
            else -> state
          }
        }
      }
    }
  }
}