package com.smparkworld.daangnmarket.data.local

import android.content.SharedPreferences
import com.smparkworld.daangnmarket.di.AppModule.AppPref
import com.smparkworld.daangnmarket.utils.PreferencesKey
import com.smparkworld.daangnmarket.utils.RsaCipherHelper
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
        @AppPref private val pref: SharedPreferences
): UserLocalDataSource {

    override suspend fun saveAccessToken(token: String, expiredIn: String) {
        pref.edit().run {
            putString(PreferencesKey.USER_ACCESS_TOKEN, RsaCipherHelper.encrypt(token))
            putString(PreferencesKey.USER_ACCESS_TOKEN_EXPIRED_IN, RsaCipherHelper.encrypt(expiredIn))
            apply()
        }
    }

    override suspend fun saveRefreshToken(token: String, expiredIn: String) {
        pref.edit().run {
            putString(PreferencesKey.USER_REFRESH_TOKEN, RsaCipherHelper.encrypt(token))
            putString(PreferencesKey.USER_REFRESH_TOKEN_EXPIRED_IN, RsaCipherHelper.encrypt(expiredIn))
            apply()
        }
    }

    override suspend fun getRefreshToken(): String? {
        return pref.getString(PreferencesKey.USER_REFRESH_TOKEN, null)?.let {
            RsaCipherHelper.decrypt(it)
        }
    }
}