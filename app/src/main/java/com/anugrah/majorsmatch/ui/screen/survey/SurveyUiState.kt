package com.anugrah.majorsmatch.ui.screen.survey

import com.anugrah.majorsmatch.data.remote.apiresponse.QuestionItem

data class SurveyUiState (
  val questions: List<QuestionItem> = emptyList(),
  val answers: MutableMap<Int, Boolean?> = mutableMapOf()
)