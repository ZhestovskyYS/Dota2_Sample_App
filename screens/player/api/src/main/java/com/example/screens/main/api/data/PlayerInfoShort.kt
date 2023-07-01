package com.example.screens.main.api.data

import android.graphics.Bitmap

data class PlayerInfoShort(
    val nickname: String,
    val avatar: Bitmap?,
    val lastOnline: String,
    val hasDotaPlus: Boolean,
    val profileLink: String, // https://www.opendota.com/players/{id}
    val steamProfileLink: String,
)