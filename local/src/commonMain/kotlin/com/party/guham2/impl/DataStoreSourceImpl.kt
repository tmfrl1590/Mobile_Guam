package com.party.guham2.impl

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.party.guham2.LocalConstants.ACCESS_TOKEN_KEY
import com.party.guham2.createDataStore
import com.party.guham2.local.DataStoreSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class DataStoreSourceImpl(): DataStoreSource {
    private val dataStore = createDataStore()

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey(ACCESS_TOKEN_KEY)
    }

    override suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun getAccessToken(): String? {
        return dataStore.data
            .catch { exception ->
                if (exception is Exception) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[ACCESS_TOKEN]
            }
            .firstOrNull()
    }
}