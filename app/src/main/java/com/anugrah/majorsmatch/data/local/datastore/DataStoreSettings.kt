package com.anugrah.majorsmatch.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings_prefs")

class DataStoreSettings(private val context: Context) {
  companion object {
    val LANGUAGE = stringPreferencesKey("language")
    val THEME = stringPreferencesKey("theme")
  }

  suspend fun saveLanguage(language: String) {
    context.dataStore.edit { preferences ->
      preferences[LANGUAGE] = language
    }
  }

  suspend fun saveTheme(theme: String)  {
    context.dataStore.edit { preferences ->
      preferences[THEME] = theme
    }
  }

  val getLanguage = context.dataStore.data.map { preferences ->
    preferences[LANGUAGE] ?: "en"
  }

  val getTheme = context.dataStore.data.map { preferences ->
    preferences[THEME] ?: "auto"
  }

  suspend fun clearSettings() {
    context.dataStore.edit { preferences ->
      preferences.clear()
    }
  }

}