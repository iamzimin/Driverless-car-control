package com.ulstu.api.domain.models

import com.google.gson.annotations.SerializedName

data class UnitInfoResponse(
    @SerializedName("unitName") val unitName: String,
    @SerializedName("fwVer") val firmwareVer: String,
    @SerializedName("hvVer") val hardwareVer: String,
    @SerializedName("buildDate") val buildDate: String,
    @SerializedName("buildTime") val buildTime: String,
)
