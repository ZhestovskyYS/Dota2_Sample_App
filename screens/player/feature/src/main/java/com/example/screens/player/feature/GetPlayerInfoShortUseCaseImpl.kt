package com.example.screens.player.feature

import com.example.entities.ProcessState
import com.example.screens.main.api.data.PlayerInfoShort
import com.example.screens.main.api.usecases.GetPlayerInfoShortUseCase
import com.example.utils.LoadImageUseCase
import com.exapmple.domain.feature.ReposProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.util.Date

class GetPlayerInfoShortUseCaseImpl : GetPlayerInfoShortUseCase {
    private val repository = ReposProvider.profileRepo
    private val loadImagesUseCase = LoadImageUseCase()

    override suspend fun invoke(profileId: String): ProcessState<PlayerInfoShort> =
        withContext(Dispatchers.IO) {
            try {
                val response = repository.getPlayerInfoShort(profileId)

                ProcessState.Success(
                    PlayerInfoShort(
                        nickname = response.nickname,
                        avatar = response.avatarUrl,
                        lastOnline = response.lastOnline,
                        hasDotaPlus = response.hasDotaPlus,
                        profileLink = response.profileLink,
                        steamProfileLink = response.steamProfileLink,
                    )
                )
            } catch (e: Exception) {
                ProcessState.Error(e)
            }
        }
}