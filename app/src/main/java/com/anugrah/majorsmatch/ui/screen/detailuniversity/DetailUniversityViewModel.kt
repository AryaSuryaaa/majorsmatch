package com.anugrah.majorsmatch.ui.screen.detailuniversity

import androidx.lifecycle.ViewModel
import com.anugrah.majorsmatch.data.dummy.universitasList
import com.anugrah.majorsmatch.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailUniversityViewModel @Inject constructor(

): ViewModel() {
  private val _uiState = MutableStateFlow<UiState<DetailUniversityUiState>>(UiState.Loading)
  val uiState: StateFlow<UiState<DetailUniversityUiState>> = _uiState

  fun getUniversityById(universityId: Int) {
    val university = universitasList.find { it.id == universityId }
    _uiState.value = UiState.Success(DetailUniversityUiState(university))
  }

}