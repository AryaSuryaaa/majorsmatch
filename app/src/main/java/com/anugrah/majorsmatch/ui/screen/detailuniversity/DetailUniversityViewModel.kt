package com.anugrah.majorsmatch.ui.screen.detailuniversity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.anugrah.majorsmatch.domain.model.University
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailUniversityViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle
) : ViewModel() {
  val university: University? = savedStateHandle.get<University>("university")
}
