package com.a2mp.diseaseidentifier.Api

import retrofit2.http.POST

interface ApiService {

    @POST("")
    fun getImage()
}