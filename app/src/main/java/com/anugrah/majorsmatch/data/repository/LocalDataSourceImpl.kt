package com.anugrah.majorsmatch.data.repository

import com.anugrah.majorsmatch.data.local.datastore.DataStoreManager
import com.anugrah.majorsmatch.data.local.datastore.DataStoreSettings
import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import com.anugrah.majorsmatch.domain.repository.ILocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
  private val dataStoreManager: DataStoreManager,
  private val dataStoreSettings: DataStoreSettings
): ILocalDataSource {
  override fun getUserSession(): Flow<DataLogin> {
    return dataStoreManager.getUserSession()
  }

  override suspend fun saveTheme(theme: String) {
    dataStoreSettings.saveTheme(theme)
  }

  override fun getTheme(): Flow<String> {
    return dataStoreSettings.getTheme
  }

  override suspend fun saveLanguage(language: String) {
    dataStoreSettings.saveLanguage(language)
  }

  override fun getLanguage(): Flow<String> {
    return dataStoreSettings.getLanguage
  }

}