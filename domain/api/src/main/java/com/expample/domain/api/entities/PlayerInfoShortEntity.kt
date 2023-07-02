package com.expample.domain.api.entities

data class PlayerInfoShortEntity(
    val nickname: String,
    val avatarUrl: String,
    val lastOnline: String,
    val hasDotaPlus: Boolean,
    val profileLink: String,
    val steamProfileLink: String,
)
