package com.example.domain.impl.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    @SerialName("account_id")
    val id: String,
    @SerialName("personaname")
    val nickname: String,
    @SerialName("avatarfull")
    val avatarUrl: String?,
)
