package com.anugrah.majorsmatch.ui.screen.explore

import androidx.lifecycle.ViewModel
import com.anugrah.majorsmatch.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(): ViewModel() {

  private val _uiState = MutableStateFlow<UiState<ExploreUiState>>(UiState.Loading)
  val uiState: StateFlow<UiState<ExploreUiState>> = _uiState

  init {
    _uiState.value = UiState.Success(ExploreUiState(query = ""))
  }

  fun updateQuery(query: String) {
    if (_uiState.value is UiState.Success) {
      val currentData = (_uiState.value as UiState.Success).data
      _uiState.value = UiState.Success(currentData.copy(query = query))
    }
  }
}