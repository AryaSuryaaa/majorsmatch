package com.anugrah.majorsmatch.data.repository

import com.anugrah.majorsmatch.data.remote.api.ApiService
import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse
import com.anugrah.majorsmatch.domain.repository.IRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
  private val apiService: ApiService
): IRemoteDataSource{
  override fun register(param: RegisterRequest): Flow<RegisterResponse> = flow {
    val response = apiService.registerUser(param)

    if (!response.status) {
      throw Exception("Register Failed, Try Again!")
    }

    emit(response)
  }.catch { e ->
    throw IOException(e.localizedMessage ?: "Periksa jaringan Anda")
  }
}