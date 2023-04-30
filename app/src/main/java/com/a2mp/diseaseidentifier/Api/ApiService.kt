package com.a2mp.diseaseidentifier.Api

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {


    @Multipart
    @POST("all")
    fun uploadAttachment(
        @Part filePart: MultipartBody.Part,
        @Part name: MultipartBody.Part,
        @Query(value = "api-key") str: String
    ): Call<String?>

    @GET("plants")
    fun getPlant(
        @Query("search") name: String
    ): Call<String?>

    @POST("health_assessment")
    fun getHealthStatusDirectFor(
        @Body string: String
    ): Call<String?>
}