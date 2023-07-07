package com.expample.domain.api.repo

import com.expample.domain.api.entities.HeroEntity
import com.expample.domain.api.entities.PlayerEntity
import com.expample.domain.api.entities.PlayerInfoEntity
import com.expample.domain.api.entities.PlayerInfoShortEntity

interface PlayerRepository {
    suspend fun fetchHeroes()
    suspend fun searchPlayer(searchPattern: String): List<PlayerEntity>
    suspend fun getPlayerInfo(playerId: String): PlayerInfoEntity
    suspend fun getPlayerInfoShort(playerId: String): PlayerInfoShortEntity
}