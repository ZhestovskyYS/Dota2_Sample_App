package com.example.domain.impl.net.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WinsLosses(
    @SerialName("win")
    val wins: Int,
    @SerialName("lose")
    val losses: Int,
) {
    val winRate: Float = wins.toFloat() / (wins + losses)
}
