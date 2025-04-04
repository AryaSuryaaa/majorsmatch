package com.anugrah.majorsmatch.domain.usecase.surveyusecase

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.data.remote.apiresponse.SurveyResponse
import com.anugrah.majorsmatch.domain.model.SurveyAnswer
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostSurveyUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<List<SurveyAnswer>, SurveyResponse>() {
  override fun execute(param: List<SurveyAnswer>): Flow<SurveyResponse> {
    return repository.postSurvey(param)
  }
}