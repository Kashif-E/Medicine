package com.kashif.core.data.remote

import com.kashif.core.domain.model.MedicineResponse
import retrofit2.http.GET

interface MedicineApi {
    @GET("api/mock/assignment")
    suspend fun getMedicines(): List<MedicineResponse>
}