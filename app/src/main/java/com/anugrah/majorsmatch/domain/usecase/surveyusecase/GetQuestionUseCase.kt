package com.anugrah.majorsmatch.domain.usecase.surveyusecase

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.data.remote.apiresponse.GetQuestionResponse
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuestionUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<Unit, GetQuestionResponse>() {
  override fun execute(param: Unit): Flow<GetQuestionResponse> {
    return repository.getQuestion()
  }
}