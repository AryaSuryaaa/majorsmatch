package com.anugrah.majorsmatch.domain.repository

import com.anugrah.majorsmatch.data.remote.apirequest.LoginRequest
import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.data.remote.apirequest.SubmitFeedbackRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.DataUniversity
import com.anugrah.majorsmatch.data.remote.apiresponse.GetQuestionResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.GetTestimonyResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.LoginResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.SubmitFeedbackResponse
import com.anugrah.majorsmatch.domain.model.University
import kotlinx.coroutines.flow.Flow

interface IRemoteDataSource {
  fun register(param: RegisterRequest): Flow<RegisterResponse>
  fun login(param: LoginRequest): Flow<LoginResponse>
  fun getUniversities(): Flow<List<University>>
  fun getTestimony(): Flow<GetTestimonyResponse>
  fun submitFeedback(param: SubmitFeedbackRequest): Flow<SubmitFeedbackResponse>
  fun searchUniversity(query: String): Flow<List<DataUniversity>>
  fun getQuestion(): Flow<GetQuestionResponse>
}