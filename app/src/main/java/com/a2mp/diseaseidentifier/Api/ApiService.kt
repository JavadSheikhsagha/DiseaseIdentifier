package com.a2mp.diseaseidentifier.Api

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {



    @Multipart
    @POST("")
    fun uploadAttachment(
        @Part filePart: MultipartBody.Part,
        @Part name: MultipartBody.Part
    ): Call<String?>?

}