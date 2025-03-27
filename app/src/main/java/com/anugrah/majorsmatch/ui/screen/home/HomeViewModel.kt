package com.anugrah.majorsmatch.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.domain.usecase.homeusecase.GetUniversitiesUseCase
import com.anugrah.majorsmatch.domain.usecase.splashusecase.GetUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val getUniversitiesUseCase: GetUniversitiesUseCase,
  private val getUserSessionUseCase: GetUserSessionUseCase
): ViewModel() {
  private val _uiState = MutableStateFlow(HomeUiState())
  val uiState get() = _uiState

  fun getUniversities() {
    viewModelScope.launch {
      getUniversitiesUseCase(Unit).collect { result ->
        _uiState.update { state ->
          when (result) {
            is ResultState.Loading -> {
              state.copy(isLoading = true)
            }

            is ResultState.Success -> {
              state.copy(isLoading = false, universities = result.data)
            }

            is ResultState.Error -> {
              state.copy(isLoading = false)
            }

            else -> state
          }
        }
      }
    }
  }

  fun getUserSession() {
    viewModelScope.launch {
      getUserSessionUseCase(Unit).collect { result ->
        _uiState.update { state ->
          when (result) {
            is ResultState.Success -> {
              state.copy(isLoading = false, userSession = result.data)
            }

            else -> state
          }
        }
      }
    }
  }
}