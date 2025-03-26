package com.anugrah.majorsmatch.domain.repository

import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface IRepository {
  suspend fun saveOnBoardingState(isCompleted: Boolean)

  fun readOnBoardingState(): Flow<Boolean>

  fun register(param: RegisterRequest): Flow<RegisterResponse>
}