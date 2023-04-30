package com.a2mp.diseaseidentifier.repos

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.a2mp.diseaseidentifier.Api.ApiClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.ByteArrayOutputStream
import java.io.File


class LocalRepository() {

    private val apiService = ApiClient.getIdentifyRetrofit()
    private val identifyService = ApiClient.getFindPlantByNameRetrofit()
    private val getHealthService = ApiClient.getHealthStatusDirectForRetrofit()

    fun identify(file: File) : Call<String?> {
        val filePart = MultipartBody.Part.createFormData(
            "images",
            "file.jpg",
            RequestBody.create("image/*".toMediaTypeOrNull(), file)
        )
        val name = MultipartBody.Part.createFormData(
            "organs",
            "leaf"
        )
        return identifyService.uploadAttachment(filePart, name)
    }

    fun getHealthStatusDirectFor(file:File) : Call<String?> {
        val bm = BitmapFactory.decodeFile(file.path)
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos) // bm is the bitmap object

        val b: ByteArray = baos.toByteArray()
        val bytes = Base64.encodeToString(b, Base64.DEFAULT)


        return getHealthService.getHealthStatusDirectFor("")
    }

    fun getPlant(searchStr: String) : Call<String?> {
        return apiService.getPlant(searchStr)
    }


}