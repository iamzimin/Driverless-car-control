package com.ulstu.api.domain.service

import com.ulstu.api.domain.models.SystemInfoResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface DccApi {
    @GET("web/system.json")
    suspend fun getSystemInfo(): SystemInfoResponse
}