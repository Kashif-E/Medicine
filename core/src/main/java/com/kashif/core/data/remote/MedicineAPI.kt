package com.kashif.core.data.remote

import com.kashif.core.domain.model.MedicineResponse
import retrofit2.http.GET

interface MedicineApi {
    @GET("e64438a7-7966-492f-85b6-87470ea4d84f")
    suspend fun getMedicines(): MedicineResponse
}