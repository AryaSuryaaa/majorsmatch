package com.anugrah.majorsmatch.domain.usecase.submitfeedbackusecase

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.data.remote.apirequest.SubmitFeedbackRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.SubmitFeedbackResponse
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubmitFeedbackUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<SubmitFeedbackRequest, SubmitFeedbackResponse>() {
  override fun execute(param: SubmitFeedbackRequest): Flow<SubmitFeedbackResponse> {
    return repository.submitFeedback(param)
  }
}