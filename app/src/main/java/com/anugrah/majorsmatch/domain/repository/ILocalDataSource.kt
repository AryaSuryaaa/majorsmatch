package com.anugrah.majorsmatch.domain.repository

import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
  fun getUserSession(): Flow<DataLogin>
  suspend fun saveTheme(theme: String)
  fun getTheme(): Flow<String>
  suspend fun saveLanguage(language: String)
  fun getLanguage(): Flow<String>
}