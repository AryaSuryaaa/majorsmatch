package com.anugrah.majorsmatch.ui.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.domain.usecase.settingsusecase.GetLanguageUseCase
import com.anugrah.majorsmatch.domain.usecase.settingsusecase.GetThemeUseCase
import com.anugrah.majorsmatch.domain.usecase.settingsusecase.SaveLanguageUseCase
import com.anugrah.majorsmatch.domain.usecase.settingsusecase.SaveThemeUseCase
import com.anugrah.majorsmatch.domain.usecase.splashusecase.GetUserSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
  private val getUserSessionUseCase: GetUserSessionUseCase,
  private val saveThemeUseCase: SaveThemeUseCase,
  private val getThemeUseCase: GetThemeUseCase,
  private val saveLanguageUseCase: SaveLanguageUseCase,
  private val getLanguageUseCase: GetLanguageUseCase
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

  fun saveTheme(theme: String) {
    viewModelScope.launch {
      saveThemeUseCase(theme)
    }
  }

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

  fun saveLanguage(language: String) {
    viewModelScope.launch {
      saveLanguageUseCase(language)
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