package com.anugrah.majorsmatch.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.anugrah.majorsmatch.data.remote.apiresponse.DataLogin
import com.anugrah.majorsmatch.data.remote.apiresponse.DataUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {
  companion object {
    val TOKEN = stringPreferencesKey("token")
    val USERNAME = stringPreferencesKey("username")
    val FULL_NAME = stringPreferencesKey("fullname")
    val EMAIL = stringPreferencesKey("email")
    val ID = intPreferencesKey("id")
  }

  suspend fun saveUserSession(dataLogin: DataLogin) {
    context.dataStore.edit { preferences ->
      preferences[TOKEN] = dataLogin.token
      preferences[USERNAME] = dataLogin.dataUser.username
      preferences[FULL_NAME] = dataLogin.dataUser.fullName
      preferences[EMAIL] = dataLogin.dataUser.email
      preferences[ID] = dataLogin.dataUser.id

    }
  }

  fun getUserSession(): Flow<DataLogin> {
    return context.dataStore.data.map { preferences ->
      DataLogin(
        token = preferences[TOKEN] ?: "",
        dataUser = DataUser(
          username = preferences[USERNAME] ?: "",
          fullName = preferences[FULL_NAME] ?: "",
          email = preferences[EMAIL] ?: "",
          id = preferences[ID] ?: 0,
        )
      )
    }
  }

  suspend fun clearUserSession() {
    context.dataStore.edit { preferences ->
      preferences.clear()
    }
  }

}