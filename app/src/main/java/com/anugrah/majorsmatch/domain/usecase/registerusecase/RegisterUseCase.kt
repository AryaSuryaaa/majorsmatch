package com.anugrah.majorsmatch.domain.usecase.registerusecase

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<RegisterRequest, RegisterResponse>() {
  override fun execute(param: RegisterRequest): Flow<RegisterResponse> {
    return repository.register(param)
  }
}