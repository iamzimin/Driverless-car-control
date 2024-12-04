package com.ulstu.block_scanner.domain.mapper

import com.ulstu.api.domain.models.SystemInfoResponse
import com.ulstu.block_scanner.domain.model.SystemInfo

fun SystemInfoResponse.toSystemInfo(): SystemInfo {
    return SystemInfo(
        dhcp = dhcp,
        ip = "${ip1}.${ip2}.${ip3}.${ip4}",
        ma = "${ma1}.${ma2}.${ma3}.${ma4}",
        gw = "${gw1}.${gw2}.${gw3}.${gw4}",
    )
}
