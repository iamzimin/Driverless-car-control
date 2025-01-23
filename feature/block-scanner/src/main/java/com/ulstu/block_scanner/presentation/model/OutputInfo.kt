package com.ulstu.block_scanner.presentation.model

import android.content.Context
import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.RequestResult
import com.ulstu.block_scanner.domain.model.PingStatus
import com.ulstu.block_scanner.domain.model.BlockInfo
import com.ulstu.resource.R

data class OutputInfo(
    val foundedIps: List<PingStatus>? = null,
    val foundedBlocks: List<RequestResult<BlockInfo, NetworkError>>? = null,
) {
    fun toConsole(context: Context): String {
        val builder = StringBuilder()

        if (foundedIps.isNullOrEmpty()) {
            builder.append("${context.getString(R.string.no_ip_found_network)}.")
        } else {
            builder.append("${context.getString(R.string.scanning_ip_addresses)}:\n\n")
            foundedIps.forEach { ip ->
                when (ip) {
                    is PingStatus.PingSuccess -> builder.append("${ip.ip} - ${context.getString(R.string.success)}\n")
                    is PingStatus.PingFail -> builder.append("${ip.ip} - ${context.getString(R.string.fail)}\n")
                }
            }
            builder.append("\n")
        }

        if (foundedBlocks == null) {
            builder.append("")
        } else if (foundedBlocks.isEmpty()) {
            builder.append("\n${context.getString(R.string.blocks_not_found)}.")
        } else {
            builder.append("\n${context.getString(R.string.detected_blocks)}:\n\n")
            foundedBlocks.forEach { block ->
                when (block) {
                    is RequestResult.Success -> builder.append("${block.data.systemInfo.ip} - ${context.getString(R.string.detected)}\n")
                    is RequestResult.Error -> {
                        val errorText = when (block.error) {
                            NetworkError.SERIALIZATION -> context.getString(R.string.error_serialization)
                            NetworkError.NOT_FOUND -> context.getString(R.string.error_not_found)
                            NetworkError.REQUEST_TIMEOUT -> context.getString(R.string.error_request_timeout)
                            NetworkError.TOO_MANY_REQUESTS -> context.getString(R.string.error_too_many_requests)
                            NetworkError.SERVER_ERROR -> context.getString(R.string.error_server_error)
                            NetworkError.CONNECT_EXCEPTION -> context.getString(R.string.error_connect_exception)
                            NetworkError.PROTOCOL_EXCEPTION -> context.getString(R.string.error_protocol_exception)
                            NetworkError.UNKNOWN -> context.getString(R.string.error_unknown)
                        }
                        builder.append("${block.url} - $errorText\n")
                    }
                }
            }
        }

        return builder.toString()
    }
}
