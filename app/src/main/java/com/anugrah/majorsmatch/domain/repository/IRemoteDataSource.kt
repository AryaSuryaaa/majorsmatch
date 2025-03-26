package com.anugrah.majorsmatch.domain.repository

import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource {
  fun register(param: RegisterRequest): Flow<RegisterResponse>
}