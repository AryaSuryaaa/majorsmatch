package com.anugrah.majorsmatch.data.remote.apirequest

import com.anugrah.majorsmatch.domain.model.SurveyAnswer

data class SurveyRequest(
  val answers: List<SurveyAnswer>
)
