package com.example.screens.main.api.usecases

import com.example.entities.ProcessState
import com.example.screens.main.api.data.PlayerInfo

interface GetPlayerInfoUseCase {
    suspend operator fun invoke(playerId: String): ProcessState<PlayerInfo>
}