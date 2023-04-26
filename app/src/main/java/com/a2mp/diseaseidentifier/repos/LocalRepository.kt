package com.a2mp.diseaseidentifier.repos

import android.content.Context
import com.a2mp.diseaseidentifier.Api.ApiClient

class LocalRepository(val context: Context) {

    private val apiService = ApiClient.getRetrofit()

    fun getIsFirstTime(): Boolean {

        return context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
            .getBoolean("isFirstTime", true)
    }

    fun setIsFirstTimeTrue() {
        context.getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("isFirstTime", false)
            .apply()
    }
}