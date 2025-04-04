package com.anugrah.majorsmatch.ui.screen.survey

import com.anugrah.majorsmatch.common.ResultState
import com.anugrah.majorsmatch.data.remote.apiresponse.QuestionItem
import com.anugrah.majorsmatch.data.remote.apiresponse.SurveyResponse

data class SurveyUiState (
  val questions: List<QuestionItem> = emptyList(),
  val answers: MutableMap<Int, Boolean?> = mutableMapOf(),
  val surveyResult: ResultState<SurveyResponse> = ResultState.Idle
)