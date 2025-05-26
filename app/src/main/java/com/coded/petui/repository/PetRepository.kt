package com.coded.petui.repository

import com.coded.petui.model.Pet
import com.coded.petui.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PetRepository {
    suspend fun getPets(): List<Pet> {
        return withContext(Dispatchers.IO) {
            RetrofitHelper.apiService.getPets()
        }
    }
}
