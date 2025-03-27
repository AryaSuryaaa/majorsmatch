package com.anugrah.majorsmatch.domain.repository

import com.anugrah.majorsmatch.data.remote.apirequest.LoginRequest
import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import com.anugrah.majorsmatch.data.remote.apiresponse.GetUniversitiesResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.LoginResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse
import com.anugrah.majorsmatch.domain.model.University
import kotlinx.coroutines.flow.Flow

interface IRepository {
  suspend fun saveOnBoardingState(isCompleted: Boolean)

  fun readOnBoardingState(): Flow<Boolean>

  fun register(param: RegisterRequest): Flow<RegisterResponse>
  fun login(param: LoginRequest): Flow<LoginResponse>
  fun getUserSession(): Flow<DataLogin>
  fun getUniversities(): Flow<List<University>>
}