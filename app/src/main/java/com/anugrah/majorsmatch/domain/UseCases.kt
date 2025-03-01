package com.anugrah.majorsmatch.domain

import com.anugrah.majorsmatch.domain.usecase.readonboarding.ReadOnBoardingUseCase
import com.anugrah.majorsmatch.domain.usecase.saveonboarding.SaveOnBoardingUseCase

data class UseCases (
  val saveOnBoardingUseCase: SaveOnBoardingUseCase,
  val readOnBoardingUseCase: ReadOnBoardingUseCase
)