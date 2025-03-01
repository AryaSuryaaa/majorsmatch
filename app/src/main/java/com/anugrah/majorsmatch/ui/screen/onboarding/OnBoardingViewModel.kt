package com.anugrah.majorsmatch.ui.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anugrah.majorsmatch.domain.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
  private val useCases: UseCases
): ViewModel() {
  fun saveOnBoardingState(isCompleted: Boolean) {
    viewModelScope.launch(Dispatchers.IO) {
      useCases.saveOnBoardingUseCase.invoke(isCompleted = isCompleted)
    }
  }
}