package com.anugrah.majorsmatch.data.remote.api

import com.anugrah.majorsmatch.data.remote.apirequest.LoginRequest
import com.anugrah.majorsmatch.data.remote.apirequest.RegisterRequest
import com.anugrah.majorsmatch.data.remote.apiresponse.GetUniversitiesResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.LoginResponse
import com.anugrah.majorsmatch.data.remote.apiresponse.RegisterResponse
import com.anugrah.majorsmatch.domain.model.University
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
  @POST("auth/register")
  suspend fun registerUser(
    @Body request: RegisterRequest
  ): RegisterResponse

  @POST("auth/login")
  suspend fun loginUser(
    @Body request: LoginRequest
  ): LoginResponse

  @GET("survey/universities")
  suspend fun getUniversities(): GetUniversitiesResponse
}