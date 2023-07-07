package com.example.domain.impl.net.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroDto(
    @SerialName("hero_id")
    val id: Int,
    @SerialName("last_played")
    val lastPlayed: Long,
    val games: Int,
)