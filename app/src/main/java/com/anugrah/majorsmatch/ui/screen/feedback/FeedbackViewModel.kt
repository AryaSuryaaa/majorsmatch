package com.anugrah.majorsmatch.ui.screen.feedback

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.data.remote.apirequest.SubmitFeedbackRequest
import com.anugrah.majorsmatch.domain.usecase.homeusecase.GetTestimonyUseCase
import com.anugrah.majorsmatch.domain.usecase.splashusecase.GetUserSessionUseCase
import com.anugrah.majorsmatch.domain.usecase.submitfeedbackusecase.SubmitFeedbackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedbackViewModel @Inject constructor(
  private val getTestimonyUseCase: GetTestimonyUseCase,
  private val submitFeedbackUseCase: SubmitFeedbackUseCase,
  private val getUserSessionUseCase: GetUserSessionUseCase
): ViewModel() {
  private val _uiState = MutableStateFlow(FeedbackUiState())
  val uiState get() = _uiState

  fun getUserSession() {
    viewModelScope.launch {
      getUserSessionUseCase(Unit).collect { result ->
        _uiState.update { state ->
          when(result) {
            is ResultState.Success -> {
              state.copy(dataLogin = result.data)
            }
            else -> state
          }
        }
      }
    }

  }

  fun getTestimony() {
    viewModelScope.launch {
      getTestimonyUseCase(Unit).collect { result ->
        _uiState.update { state ->
          when (result) {
            is ResultState.Loading -> {
              state.copy(isLoading = true)
            }
            is ResultState.Success -> {
              state.copy(isLoading = false, testimony = result.data.dataTestimony)
            }
            else -> state
          }
        }
      }
    }
  }

  fun updateInputTestimony(input: String) {
    _uiState.update { state ->
      state.copy(inputTestimony = input)
    }
  }

  fun submitTestimony() {
    if (_uiState.value.inputTestimony.isNotEmpty() && _uiState.value.dataLogin != null) {
      val param = SubmitFeedbackRequest(
        fullName = _uiState.value.dataLogin!!.dataUser.fullName,
        email = _uiState.value.dataLogin!!.dataUser.email,
        testimony = _uiState.value.inputTestimony
      )
      viewModelScope.launch {
        submitFeedbackUseCase(param).collect { result ->
          _uiState.update { state ->
            when (result) {
              is ResultState.Loading -> {
                state.copy(submitResult = ResultState.Loading)
              }
              is ResultState.Success -> {
                state.copy(submitResult = ResultState.Success(result.data))
              }
              is ResultState.Error -> {
                state.copy(submitResult = ResultState.Error(result.error))
              }
              else -> state
            }
          }
        }
      }
    }
  }
}