package com.a2mp.diseaseidentifier.repos

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.a2mp.diseaseidentifier.Api.ApiClient
import com.a2mp.diseaseidentifier.viewmodel.imageBitmap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.ByteBuffer


class LocalRepository {

    private val apiService = ApiClient.getIdentifyRetrofit()
    private val identifyService = ApiClient.getFindPlantByNameRetrofit()
    private val getHealthService = ApiClient.getHealthStatusDirectForRetrofit()

    fun identify(file: Bitmap) : Call<String?> {
        val stream = ByteArrayOutputStream()
        file.compress(Bitmap.CompressFormat.JPEG, 40, stream)
        val size: Int = file.rowBytes * file.height
        val byteBuffer: ByteBuffer = ByteBuffer.allocate(size)
        file.copyPixelsToBuffer(byteBuffer)
        val byteArray = byteBuffer.array()
        val mybArray = stream.toByteArray()
        val filePart = MultipartBody.Part.createFormData(
            "images",
            "file.jpg",
            RequestBody.create("image/*".toMediaTypeOrNull(), mybArray)
        )
        val name = MultipartBody.Part.createFormData(
            "organs",
            "leaf"
        )
        val list = listOf(
            "2b106pU6cNDPagMxUzrMbMGl",
            "2b10Dv6vk1WRSwa9a1Tphfg5Ze",
            "2b10bONfayDzFkcL24CYw1Pu",
            "2b10fxZUCvXw0Yxx2MlRTimdu"
        )
        return apiService.uploadAttachment(filePart, name, list.random())
    }

    fun getHealthStatusDirectFor() : Call<String?> {
        val bm = imageBitmap
        val baos = ByteArrayOutputStream()
        bm?.compress(Bitmap.CompressFormat.JPEG, 100, baos) // bm is the bitmap object

        val b: ByteArray = baos.toByteArray()
        val bytes = Base64.encodeToString(b, Base64.DEFAULT)




        return getHealthService.getHealthStatusDirectFor("json")
    }

    fun getPlant(searchStr: String) : Call<String?> {
        return identifyService.getPlant(searchStr)
    }


}