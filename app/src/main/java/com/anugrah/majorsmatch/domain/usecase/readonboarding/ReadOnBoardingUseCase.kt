package com.anugrah.majorsmatch.domain.usecase.readonboarding

import com.anugrah.majorsmatch.common.BaseUseCase
import com.anugrah.majorsmatch.data.repository.Repository
import com.anugrah.majorsmatch.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnBoardingUseCase @Inject constructor(
  private val repository: IRepository
): BaseUseCase<Unit, Boolean>() {
  override fun execute(param: Unit): Flow<Boolean> {
    return repository.readOnBoardingState()
  }
}