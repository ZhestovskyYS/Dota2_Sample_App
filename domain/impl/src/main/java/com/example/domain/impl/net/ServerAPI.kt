package com.example.domain.impl.net

import com.example.domain.impl.dto.HeroDto
import com.example.domain.impl.dto.HeroInfoDto
import com.example.domain.impl.dto.PlayerDto
import com.example.domain.impl.dto.PlayerInfoDto
import com.example.domain.impl.dto.WinsLosses
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ServerAPI(
    private val client: HttpClient
) {
    suspend fun getHeroes(): Map<Int, HeroInfoDto> =
        client
            .get("/constants/heroes")
            .body()


    suspend fun searchPlayers(searchPattern: String): List<PlayerDto> =
        client
            .get("search?q=$searchPattern")
            .body()

    suspend fun getPlayerInfo(profileId: String): PlayerInfoDto =
        client
            .get("/players/$profileId")
            .body()

    suspend fun getPlayerWinsLosses(profileId: String): WinsLosses =
        client
            .get("/players/$profileId/wl")
            .body()

    suspend fun getPlayerHeroes(profileId: String): List<HeroDto> =
        client
            .get("/players/$profileId/heroes")
            .body()
}