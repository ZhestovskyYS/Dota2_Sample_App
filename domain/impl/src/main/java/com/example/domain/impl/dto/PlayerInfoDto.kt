package com.example.domain.impl.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerInfoDto(
    @SerialName("mmr_estimate")
    val mmr: MmrEstimate,
    val profile: Profile,
)

@Serializable
data class MmrEstimate(
    val estimate: Int? = null,
)

@Serializable
data class Profile(
    @SerialName("account_id")
    val id: Int,
    @SerialName("personaname")
    val nickname: String,
    @SerialName("avatar")
    val avatarUrl: String,
    @SerialName("plus")
    val hasDotaPlus: Boolean,
    @SerialName("profileurl")
    val steamProfileLink: String,
    @SerialName("last_login")
    val lastLogin: String?,
)