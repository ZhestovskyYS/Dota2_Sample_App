package com.example.screens.main.api.data

import android.graphics.Bitmap
import java.util.Date

data class PlayerInfo(
    val nickname: String,
    val avatar: Bitmap?,
    val lastOnline: Date,
    val hasDotaPlus: Boolean,
    val mmr: Int,
    val wins: Int,
    val losses: Int,
    val winRate: Float,
    val mostPlayedHeroName: String,
    val mostPlayedHeroImage: Bitmap?,
    val profileLink: String, // https://www.opendota.com/players/{id}
    val steamProfileLink: String,
)
