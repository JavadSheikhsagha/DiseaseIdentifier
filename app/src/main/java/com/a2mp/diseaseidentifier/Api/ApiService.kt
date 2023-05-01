package com.a2mp.diseaseidentifier.Api

import com.a2mp.diseaseidentifier.models.DiseaseRequestModel
import com.a2mp.diseaseidentifier.models.DiseaseResponseModel
import com.a2mp.diseaseidentifier.models.IdentifyModel
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
    ): Call<IdentifyModel?>

    @GET("plants")
    fun getPlant(
        @Query("search") name: String
    ): Call<String?>


    @POST("health_assessment")
    fun getHealthStatusDirectFor(
        @Body string: DiseaseRequestModel
    ): Call<DiseaseResponseModel?>
}