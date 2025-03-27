package com.anugrah.majorsmatch.data.repository

import com.anugrah.majorsmatch.data.local.datastore.DataStoreManager
import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import com.anugrah.majorsmatch.domain.repository.ILocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
  private val dataStore: DataStoreManager
): ILocalDataSource {
  override fun getUserSession(): Flow<DataLogin> {
    return dataStore.getUserSession()
  }
}