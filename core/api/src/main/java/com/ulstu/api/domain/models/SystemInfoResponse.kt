package com.ulstu.api.domain.models

import com.google.gson.annotations.SerializedName

data class SystemInfoResponse(
    @SerializedName("dhcp") val dhcp: String,
    @SerializedName("ip1") val ip1: String,
    @SerializedName("ip2") val ip2: String,
    @SerializedName("ip3") val ip3: String,
    @SerializedName("ip4") val ip4: String,
    @SerializedName("ma1") val ma1: String,
    @SerializedName("ma2") val ma2: String,
    @SerializedName("ma3") val ma3: String,
    @SerializedName("ma4") val ma4: String,
    @SerializedName("gw1") val gw1: String,
    @SerializedName("gw2") val gw2: String,
    @SerializedName("gw3") val gw3: String,
    @SerializedName("gw4") val gw4: String,
)
