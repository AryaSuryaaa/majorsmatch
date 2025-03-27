package com.anugrah.majorsmatch.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.domain.UseCases
import com.anugrah.majorsmatch.domain.usecase.readonboarding.ReadOnBoardingUseCase
import com.anugrah.majorsmatch.domain.usecase.splashusecase.GetUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
  private val readOnBoardingUseCase: ReadOnBoardingUseCase,
  private val getUserSessionUseCase: GetUserSessionUseCase
): ViewModel() {
//  private val _onBoardingIsCompleted = MutableStateFlow(false)
//  val onBoardingIsCompleted: StateFlow<Boolean> = _onBoardingIsCompleted

  private val _uiState = MutableStateFlow(SplashUiState())
  val uiState: StateFlow<SplashUiState> = _uiState

  init {
    readOnBoarding()
    getUserSession()
  }

  private fun readOnBoarding() {
    viewModelScope.launch {
      readOnBoardingUseCase(Unit).collect { result ->
        _uiState.update {
          when(result) {
            is ResultState.Success -> it.copy(onBoarding = result.data)
            else -> it
          }
        }
      }
    }
  }

  private fun getUserSession() {
    viewModelScope.launch {
      getUserSessionUseCase(Unit).collect { result ->
        _uiState.update {
          when(result) {
            is ResultState.Success -> it.copy(isLogin = result.data)
            else -> it
          }
        }
      }
    }
  }
}