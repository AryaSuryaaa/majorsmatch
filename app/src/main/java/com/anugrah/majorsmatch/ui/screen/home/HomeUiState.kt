package com.anugrah.majorsmatch.ui.screen.home

import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import com.anugrah.majorsmatch.data.remote.apiresponse.DataTestimony
import com.anugrah.majorsmatch.domain.model.University

data class HomeUiState (
  val isLoading: Boolean = false,
  val universities: List<University> = emptyList(),
  val userSession: DataLogin? = null,
  val testimony: List<DataTestimony> = emptyList()
)