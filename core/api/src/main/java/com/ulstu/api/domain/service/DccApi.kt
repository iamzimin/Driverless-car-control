package com.ulstu.api.domain.service

import com.ulstu.api.domain.models.SystemInfoResponse
import com.ulstu.api.domain.models.UnitInfoResponse
import retrofit2.http.GET

interface DccApi {
    @GET("web/system.json")
    suspend fun getSystemInfo(): SystemInfoResponse
    @GET("unit-info.json")
    suspend fun getUnitInfo(): UnitInfoResponse
}