package com.anugrah.majorsmatch.domain.usecase.readonboarding

import com.anugrah.majorsmatch.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
  private val repository: Repository
) {
  operator fun invoke(): Flow<Boolean> = repository.readOnBoardingState()
}