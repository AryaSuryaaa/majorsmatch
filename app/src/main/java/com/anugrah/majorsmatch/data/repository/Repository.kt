package com.anugrah.majorsmatch.data.repository

import com.anugrah.majorsmatch.data.remote.apirequest.LoginRequest
import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.data.remote.apirequest.SubmitFeedbackRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import com.anugrah.majorsmatch.data.remote.apiresponse.DataUniversity
import com.anugrah.majorsmatch.data.remote.apiresponse.GetTestimonyResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.GetUniversitiesResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.LoginResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.SubmitFeedbackResponse
import com.anugrah.majorsmatch.domain.model.University
import com.anugrah.majorsmatch.domain.repository.ILocalDataSource
import com.anugrah.majorsmatch.domain.repository.IRemoteDataSource
import com.anugrah.majorsmatch.domain.repository.IRepository
import com.anugrah.majorsmatch.domain.repository.OnBoardingOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
  private val dataStore: OnBoardingOperations,
  private val remoteDataSource: IRemoteDataSource,
  private val localDataSource: ILocalDataSource
): IRepository {
  override suspend fun saveOnBoardingState(isCompleted: Boolean) {
    dataStore.saveOnBoardingState(isCompleted = isCompleted)
  }

  override fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()
  override fun register(param: RegisterRequest): Flow<RegisterResponse> {
    return remoteDataSource.register(param)
  }

  override fun login(param: LoginRequest): Flow<LoginResponse> {
    return remoteDataSource.login(param)
  }

  override fun getUserSession(): Flow<DataLogin> {
    return localDataSource.getUserSession()
  }

  override fun getUniversities(): Flow<List<University>> {
    return remoteDataSource.getUniversities()
  }

  override fun getTestimony(): Flow<GetTestimonyResponse> {
    return remoteDataSource.getTestimony()
  }

  override fun submitFeedback(param: SubmitFeedbackRequest): Flow<SubmitFeedbackResponse> {
    return remoteDataSource.submitFeedback(param)
  }

  override suspend fun saveTheme(theme: String) {
    return localDataSource.saveTheme(theme)
  }

  override fun getTheme(): Flow<String> {
    return localDataSource.getTheme()
  }

  override suspend fun saveLanguage(language: String) {
    return localDataSource.saveLanguage(language)
  }

  override fun getLanguage(): Flow<String> {
    return localDataSource.getLanguage()
  }

  override fun searchUniversity(query: String): Flow<List<DataUniversity>> {
    return remoteDataSource.searchUniversity(query)
  }
}