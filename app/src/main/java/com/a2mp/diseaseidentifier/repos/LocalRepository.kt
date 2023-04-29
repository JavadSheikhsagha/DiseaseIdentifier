package com.a2mp.diseaseidentifier.repos

import android.content.Context
import com.a2mp.diseaseidentifier.Api.ApiClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


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

    fun identify(file: File) {
        val filePart = MultipartBody.Part.createFormData(
            "images",
            "file.jpg",
            RequestBody.create("image/*".toMediaTypeOrNull(), file)
        )
        val name = MultipartBody.Part.createFormData(
            "organs",
            "leaf"
        )
        apiService.uploadAttachment(filePart, name)
    }
}