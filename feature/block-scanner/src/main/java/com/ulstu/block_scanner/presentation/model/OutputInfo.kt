package com.ulstu.block_scanner.presentation.model

import android.content.Context
import com.ulstu.api.domain.utils.NetworkError
import com.ulstu.api.domain.utils.RequestResult
import com.ulstu.block_scanner.domain.model.PingStatus
import com.ulstu.block_scanner.domain.model.SystemInfo
import com.ulstu.resource.R

data class OutputInfo(
    val foundedIps: List<PingStatus>? = null,
    val foundedBlocks: List<RequestResult<SystemInfo?, NetworkError>>? = null,
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
                    is RequestResult.Success -> builder.append("${block.data?.ip} - ${context.getString(R.string.found)}\n")
                    is RequestResult.Error -> builder.append("${block.url} - ${context.getString(R.string.error)} ${block.error}\n")
                }
            }
        }

        return builder.toString()
    }
}
