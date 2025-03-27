package com.anugrah.majorsmatch.domain.usecase.splashusecase

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserSessionUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<Unit, DataLogin>() {
  override fun execute(param: Unit): Flow<DataLogin> {
    return repository.getUserSession()
  }
}