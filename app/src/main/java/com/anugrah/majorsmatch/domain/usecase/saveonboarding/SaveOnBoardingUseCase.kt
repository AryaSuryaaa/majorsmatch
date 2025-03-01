package com.anugrah.majorsmatch.domain.usecase.saveonboarding

import com.anugrah.majorsmatch.data.repository.Repository

class SaveOnBoardingUseCase(
  private val repository: Repository
) {
  suspend operator fun invoke(isCompleted: Boolean) {
    repository.saveOnBoardingState(isCompleted = isCompleted)
  }
}