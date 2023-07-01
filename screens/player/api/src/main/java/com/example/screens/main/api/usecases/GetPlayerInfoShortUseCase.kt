package com.example.screens.main.api.usecases

import com.example.entities.ProcessState
import com.example.screens.main.api.data.PlayerInfoShort

interface GetPlayerInfoShortUseCase {
    suspend operator fun invoke(profileId: String): ProcessState<PlayerInfoShort>
}