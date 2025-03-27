package com.anugrah.majorsmatch.domain.usecase.homeusecase

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.data.remote.apiresponse.GetTestimonyResponse
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTestimonyUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<Unit, GetTestimonyResponse>(){
  override fun execute(param: Unit): Flow<GetTestimonyResponse> {
    return repository.getTestimony()
  }
}