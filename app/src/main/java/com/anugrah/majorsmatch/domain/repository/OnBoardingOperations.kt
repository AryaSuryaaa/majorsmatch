package com.anugrah.majorsmatch.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnBoardingOperations {
  suspend fun saveOnBoardingState(isCompleted: Boolean)
  fun readOnBoardingState(): Flow<Boolean>

}