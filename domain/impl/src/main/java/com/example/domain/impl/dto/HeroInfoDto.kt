package com.example.domain.impl.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroInfoDto(
    val id: Int,
    @SerialName("localized_name")
    val name: String,
    @SerialName("img")
    val avatarSuffix: String,
)