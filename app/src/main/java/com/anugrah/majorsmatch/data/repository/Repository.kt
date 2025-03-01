package com.anugrah.majorsmatch.data.repository

import com.anugrah.majorsmatch.domain.repository.OnBoardingOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
  private val dataStore: OnBoardingOperations,
) {
  suspend fun saveOnBoardingState(isCompleted: Boolean) {
    dataStore.saveOnBoardingState(isCompleted = isCompleted)
  }

  fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()



}