package com.anugrah.majorsmatch.ui.screen.explore

import com.anugrah.majorsmatch.data.remote.apiresponse.DataUniversity

data class ExploreUiState (
  val query: String = "",
  val universities: List<DataUniversity> = emptyList(),
  val errorMessage: String? = null,
  val isLoading: Boolean = false
)