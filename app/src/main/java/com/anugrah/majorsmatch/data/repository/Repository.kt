package com.anugrah.majorsmatch.data.repository

import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse
import com.anugrah.majorsmatch.domain.repository.IRemoteDataSource
import com.anugrah.majorsmatch.domain.repository.IRepository
import com.anugrah.majorsmatch.domain.repository.OnBoardingOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
  private val dataStore: OnBoardingOperations,
  private val remoteDataSource: IRemoteDataSource
): IRepository {
  override suspend fun saveOnBoardingState(isCompleted: Boolean) {
    dataStore.saveOnBoardingState(isCompleted = isCompleted)
  }

  override fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()
  override fun register(param: RegisterRequest): Flow<RegisterResponse> {
    return remoteDataSource.register(param)
  }
}