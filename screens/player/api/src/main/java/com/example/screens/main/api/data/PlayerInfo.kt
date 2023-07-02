package com.example.screens.main.api.data

data class PlayerInfo(
    val nickname: String,
    val avatar: String,
    val lastOnline: String,
    val hasDotaPlus: Boolean,
    val mmr: Int,
    val wins: Int,
    val losses: Int,
    val winRate: Float,
    val mostPlayedHeroName: String,
    val mostPlayedHeroImageUrl: String, // https://cdn.cloudflare.steamstatic.com/apps/dota2/images/dota_react/heroes/juggernaut.png?
    val profileLink: String, // https://www.opendota.com/players/{id}
    val steamProfileLink: String,
)
