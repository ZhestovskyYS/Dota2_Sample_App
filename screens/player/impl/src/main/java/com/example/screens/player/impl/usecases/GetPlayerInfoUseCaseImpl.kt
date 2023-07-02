package com.example.screens.player.impl.usecases

import com.example.entities.ProcessState
import com.example.screens.main.api.data.PlayerInfo
import com.example.screens.main.api.usecases.GetPlayerInfoUseCase
import com.exapmple.domain.feature.ReposProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPlayerInfoUseCaseImpl : GetPlayerInfoUseCase {
    private val repository = ReposProvider.profileRepo

    override suspend fun invoke(playerId: String): ProcessState<PlayerInfo> =
        withContext(Dispatchers.IO) {
            try {
                val response = repository.getPlayerInfo(playerId)
                ProcessState.Success(
                    PlayerInfo(
                        nickname = response.nickname,
                        avatar = response.avatarUrl,
                        lastOnline = response.lastOnline,
                        hasDotaPlus = response.hasDotaPlus,
                        mmr = response.mmr,
                        winRate = response.winRate,
                        wins = response.wins,
                        losses = response.losses,
                        mostPlayedHeroName = response.mostPlayedHeroName,
                        mostPlayedHeroImageUrl = response.mostPlayedHeroImageUrl,
                        profileLink = response.profileLink,
                        steamProfileLink = response.steamProfileLink,
                    )
                )
            } catch (e: Exception) {
                ProcessState.Error(e)
            }
        }

}