package com.example.screens.main.impl.usecases

import com.example.screens.main.api.data.Player
import com.example.screens.main.api.usecases.GetPlayersUseCase

class GetPlayerUseCaseImpl : GetPlayersUseCase {

    override suspend fun invoke(searchPattern: String): List<Player> {
        return emptyList()
    }

}