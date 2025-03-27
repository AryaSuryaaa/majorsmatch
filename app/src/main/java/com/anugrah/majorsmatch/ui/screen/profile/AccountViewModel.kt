package com.anugrah.majorsmatch.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.domain.usecase.splashusecase.GetUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
  private val getUserSessionUseCase: GetUserSessionUseCase
): ViewModel() {
  private val _uiState = MutableStateFlow(AccountUiState())
  val uiState get() = _uiState

  init {
    getUserSession()
  }

  private fun getUserSession() {
    viewModelScope.launch {
      getUserSessionUseCase(Unit).collect { result ->
        _uiState.update { state ->
          when (result) {
            is ResultState.Success -> {
              state.copy(dataUser = result.data.dataUser)
            }
            else -> state
          }
        }
      }
    }
  }
}