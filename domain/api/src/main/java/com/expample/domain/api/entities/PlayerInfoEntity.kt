package com.expample.domain.api.entities

data class PlayerInfoEntity(
    val nickname: String,
    val avatarUrl: String,
    val lastOnline: String,
    val hasDotaPlus: Boolean,
    val mmr: Int,
    val wins: Int,
    val losses: Int,
    val winRate: Float,
    val mostPlayedHeroName: String,
    val mostPlayedHeroImageUrl: String,
    val profileLink: String,
    val steamProfileLink: String,
)
