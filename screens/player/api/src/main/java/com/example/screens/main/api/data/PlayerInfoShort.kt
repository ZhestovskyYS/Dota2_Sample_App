package com.example.screens.main.api.data

data class PlayerInfoShort(
    val nickname: String,
    val avatar: String?,
    val lastOnline: String,
    val hasDotaPlus: Boolean,
    val profileLink: String, // https://www.opendota.com/players/{id}
    val steamProfileLink: String,
)