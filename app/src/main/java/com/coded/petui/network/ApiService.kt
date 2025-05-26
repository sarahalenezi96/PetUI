package com.coded.petui.network

import com.coded.petui.model.Pet
import retrofit2.http.GET

interface ApiService {
    @GET("pets")
    suspend fun getPets(): List<Pet>
}
