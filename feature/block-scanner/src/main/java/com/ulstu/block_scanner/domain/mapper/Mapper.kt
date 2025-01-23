package com.ulstu.block_scanner.domain.mapper

import com.ulstu.api.domain.models.PingStatusResponse
import com.ulstu.api.domain.models.BlockInfoResponse
import com.ulstu.block_scanner.domain.model.PingStatus
import com.ulstu.block_scanner.domain.model.BlockInfo
import com.ulstu.block_scanner.domain.model.SystemInfo
import com.ulstu.block_scanner.domain.model.UnitInfo

fun BlockInfoResponse.toBlockInfo(): BlockInfo {
    val si = this.systemInfoResponse
    val ui = this.unitInfoResponse
    return BlockInfo(
        systemInfo = SystemInfo(
            ip = "${si.ip1}.${si.ip2}.${si.ip3}.${si.ip4}",
            ma = "${si.ma1}.${si.ma2}.${si.ma3}.${si.ma4}",
            gw = "${si.gw1}.${si.gw2}.${si.gw3}.${si.gw4}",
        ),
        unitInfo = UnitInfo(
            unitName = ui.unitName,
            firmwareVer = ui.firmwareVer,
            hardwareVer = ui.hardwareVer,
            buildDate = ui.buildDate,
            buildTime = ui.buildTime,
        )
    )
}

fun PingStatusResponse.toPingStatus(): PingStatus {
    return when (this) {
        is PingStatusResponse.PingSuccess -> PingStatus.PingSuccess(this.ip)
        is PingStatusResponse.PingFail -> PingStatus.PingFail(this.ip)
    }
}
