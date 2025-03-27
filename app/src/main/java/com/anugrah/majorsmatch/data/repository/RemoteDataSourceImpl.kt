package com.anugrah.majorsmatch.data.repository

import com.anugrah.majorsmatch.data.local.datastore.DataStoreManager
import com.anugrah.majorsmatch.data.remote.api.ApiService
import com.anugrah.majorsmatch.data.remote.apirequest.LoginRequest
import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.GetTestimonyResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.LoginResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse
import com.anugrah.majorsmatch.domain.model.University
import com.anugrah.majorsmatch.domain.model.toUniversity
import com.anugrah.majorsmatch.domain.repository.IRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
  private val apiService: ApiService,
  private val dataStore: DataStoreManager
): IRemoteDataSource{
  override fun register(param: RegisterRequest): Flow<RegisterResponse> = flow {
    val response = apiService.registerUser(param)

    if (!response.status) {
      throw Exception("Register Failed, Try Again!")
    }

    emit(response)
  }

  override fun login(param: LoginRequest): Flow<LoginResponse> = flow<LoginResponse> {
    val response = apiService.loginUser(param)
    dataStore.saveUserSession(response.dataLogin)
    emit(response)
  }

  override fun getUniversities(): Flow<List<University>> = flow {
    val response = apiService.getUniversities()
    val universities = response.data.map { it.toUniversity() }
    emit(universities)
  }

  override fun getTestimony(): Flow<GetTestimonyResponse> = flow {
    val response = apiService.getTestimony()
    emit(response)
  }


}