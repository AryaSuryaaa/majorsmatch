package com.anugrah.majorsmatch.domain.repository

import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import kotlinx.coroutines.flow.Flow

interface ILocalDataSource {
  fun getUserSession(): Flow<DataLogin>
}