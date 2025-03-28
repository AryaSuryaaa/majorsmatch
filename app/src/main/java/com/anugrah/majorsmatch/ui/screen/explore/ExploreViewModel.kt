package com.anugrah.majorsmatch.ui.screen.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.domain.usecase.searchusecase.SearchUseCase
import com.anugrah.majorsmatch.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
  private val searchUseCase: SearchUseCase
): ViewModel() {

  private val _uiState = MutableStateFlow(ExploreUiState())
  val uiState get() = _uiState


  fun updateQuery(query: String) {
    _uiState.update { state ->
      state.copy(query = query)
    }
  }

  fun searchUniversity() {
    val param = _uiState.value.query
    viewModelScope.launch {
      searchUseCase(param).collect { result ->
        _uiState.update { state ->
          when(result) {
            is ResultState.Loading -> {
              state.copy(isLoading = true)
            }
            is ResultState.Success -> {
              state.copy(
                universities = result.data,
                isLoading = false
              )
            }
            is ResultState.Error -> {
              state.copy(
                errorMessage = result.error,
                isLoading = false
              )
            }
            else -> state
          }
        }
      }
    }
  }
}