package com.ulstu.settings.presentation.mapper

import com.ulstu.settings.presentation.model.TextTileValue

fun TextTileValue.toValue(): String {
    return when (this) {
        is TextTileValue.UIntValue -> this.value.toString()
        is TextTileValue.IntValue -> this.value.toString()
        is TextTileValue.StringValue -> this.value
    }
}