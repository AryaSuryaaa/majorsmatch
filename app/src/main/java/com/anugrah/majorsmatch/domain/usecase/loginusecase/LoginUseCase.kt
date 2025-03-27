package com.anugrah.majorsmatch.domain.usecase.loginusecase

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.data.remote.apirequest.LoginRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.LoginResponse
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<LoginRequest, LoginResponse>() {
  override fun execute(param: LoginRequest): Flow<LoginResponse> {
    return repository.login(param)
  }
}