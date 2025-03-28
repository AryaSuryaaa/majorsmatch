package com.anugrah.majorsmatch.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.domain.usecase.settingsusecase.GetLanguageUseCase
import com.anugrah.majorsmatch.domain.usecase.settingsusecase.GetThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val getThemeUseCase: GetThemeUseCase,
  private val getLanguageUseCase: GetLanguageUseCase
) : ViewModel() {
  private val _uiState = MutableStateFlow(MainUiState())
  val uiState: StateFlow<MainUiState> = _uiState

  fun getTheme() {
    viewModelScope.launch {
      getThemeUseCase(Unit).collect { result ->
        _uiState.update { state ->
          when (result) {
            is ResultState.Success -> {
              state.copy(theme = result.data)
            }
            else -> state
          }
        }
      }
    }
  }

  fun getLanguage() {
    viewModelScope.launch {
      getLanguageUseCase(Unit).collect { result ->
        _uiState.update { state ->
          when (result) {
            is ResultState.Success -> {
              state.copy(language = result.data)
            }
            else -> state
          }
        }
      }
    }
  }

}
