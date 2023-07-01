package com.example.screens.player.feature

import com.example.entities.ProcessState
import com.example.screens.main.api.data.PlayerInfoShort
import com.example.screens.main.api.usecases.GetPlayerInfoShortUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.Date

class GetPlayerInfoShortUseCaseImpl : GetPlayerInfoShortUseCase {
    private val playerInfo = PlayerInfoShort(
        nickname = "Dyrachyo",
        lastOnline = "12 hours ago",
        avatar = null,
        hasDotaPlus = true,
        profileLink = "https://www.opendota.com/players/116934015",
        steamProfileLink = "https://steamcommunity.com/id/dyrachyoo/"
    )

    override suspend fun invoke(profileId: String): ProcessState<PlayerInfoShort> =
        withContext(Dispatchers.IO) {
            delay(500L)
            ProcessState.Success(playerInfo)
        }
}