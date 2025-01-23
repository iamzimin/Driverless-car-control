package com.ulstu.api.domain.mapper

import com.ulstu.api.domain.models.BlockInfoResponse
import com.ulstu.api.domain.models.SystemInfoResponse
import com.ulstu.api.domain.models.UnitInfoResponse
import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.RequestResult

fun mergeSystemAndUnitResults(
    systemResult: RequestResult<SystemInfoResponse, NetworkError>,
    unitResult: RequestResult<UnitInfoResponse, NetworkError>,
    ip: String
): RequestResult<BlockInfoResponse, NetworkError> {
    return when {
        systemResult is RequestResult.Success && unitResult is RequestResult.Success -> {
            RequestResult.Success(
                BlockInfoResponse(
                    systemResult.data,
                    unitResult.data
                )
            )
        }
        systemResult is RequestResult.Error -> RequestResult.Error(ip, systemResult.error)
        unitResult is RequestResult.Error -> RequestResult.Error(ip, unitResult.error)
        else -> RequestResult.Error(ip, NetworkError.UNKNOWN)
    }
}