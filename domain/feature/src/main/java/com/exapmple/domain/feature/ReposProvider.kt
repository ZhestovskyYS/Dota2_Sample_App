package com.exapmple.domain.feature

import com.example.domain.impl.repo.PlayerRepositoryImpl
import com.expample.domain.api.repo.PlayerRepository

object ReposProvider {
    val profileRepo: PlayerRepository = PlayerRepositoryImpl()
}