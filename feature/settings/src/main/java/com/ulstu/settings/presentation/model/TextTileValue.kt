package com.ulstu.settings.presentation.model

sealed class TextTileValue {
    data class UIntValue(val value: Int) : TextTileValue()
    data class IntValue(val value: Int) : TextTileValue()
    data class StringValue(val value: String) : TextTileValue()
}
