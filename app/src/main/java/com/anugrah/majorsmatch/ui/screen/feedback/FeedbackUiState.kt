package com.anugrah.majorsmatch.ui.screen.feedback

import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import com.anugrah.majorsmatch.data.remote.apiresponse.DataTestimony
import com.anugrah.majorsmatch.data.remote.apiresponse.GetTestimonyResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.SubmitFeedbackResponse

data class FeedbackUiState (
  val isLoading: Boolean = false,
  val testimony: List<DataTestimony> = emptyList(),
  val inputTestimony: String = "",
  val dataLogin: DataLogin? = null,
  val submitResult: ResultState<SubmitFeedbackResponse> = ResultState.Idle
)