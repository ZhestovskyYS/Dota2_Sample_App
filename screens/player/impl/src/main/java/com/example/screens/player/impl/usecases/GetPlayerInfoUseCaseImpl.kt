package com.example.screens.player.impl.usecases

import com.example.entities.ProcessState
import com.example.screens.main.api.data.PlayerInfo
import com.example.screens.main.api.usecases.GetPlayerInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class GetPlayerInfoUseCaseImpl : GetPlayerInfoUseCase {
    private val playerInfo = PlayerInfo(
        nickname = "Dyrachyo",
        lastOnline = "12 hours ago",
        avatar = null,
        hasDotaPlus = true,
        mmr = 6653,
        losses = 3160,
        wins = 3823,
        winRate = 54.75f,
        mostPlayedHeroName = "Juggernaut",
        mostPlayedHeroImage = null,
        profileLink = "https://www.opendota.com/players/116934015",
        steamProfileLink = "https://steamcommunity.com/id/dyrachyoo/",
    )

    override suspend fun invoke(playerId: String): ProcessState<PlayerInfo> =
        withContext(Dispatchers.IO) {
            delay(500L)
            ProcessState.Success(playerInfo)
        }

}