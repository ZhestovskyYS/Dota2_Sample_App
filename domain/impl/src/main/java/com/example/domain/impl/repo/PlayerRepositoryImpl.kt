package com.example.domain.impl.repo

import android.net.Uri
import com.example.domain.impl.dto.HeroInfo
import com.example.domain.impl.dto.PlayerInfoDto
import com.example.domain.impl.net.NetworkService
import com.expample.domain.api.entities.PlayerEntity
import com.expample.domain.api.entities.PlayerInfoEntity
import com.expample.domain.api.entities.PlayerInfoShortEntity
import com.expample.domain.api.repo.PlayerRepository
import io.ktor.client.request.HttpRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val NONE = "none"
private const val RES_HOST = "https://cdn.cloudflare.steamstatic.com/"
private const val PROFILE_HOST = "https://www.opendota.com/players/"

class PlayerRepositoryImpl : PlayerRepository {

    override suspend fun searchPlayer(searchPattern: String): List<PlayerEntity> =
        withContext(Dispatchers.IO) {
            NetworkService.serverAPI
                .searchPlayers(searchPattern)
                .map {
                    PlayerEntity(
                        id = it.id,
                        nickname = it.nickname,
                        avatarUrl = it.avatarUrl ?: "none"
                    )
                }
        }

    override suspend fun getPlayerInfo(playerId: String): PlayerInfoEntity =
        withContext(Dispatchers.IO) {
            val allHeroes = async(SupervisorJob()) {
                NetworkService.serverAPI.getHeroes()
            }
            val mainInfoTask = async(SupervisorJob()) {
                NetworkService.serverAPI.getPlayerInfo(playerId)
            }
            val heroesTask = async(SupervisorJob()) {
                NetworkService.serverAPI.getPlayerHeroes(playerId)
            }
            val winsLossesTask = async(SupervisorJob()) {
                NetworkService.serverAPI.getPlayerWinsLosses(playerId)
            }


            val mainInfo = mainInfoTask.await()
            val mostPlayedHero = heroesTask.await().maxBy { it.games }
            val mostPlayedHeroInfo = allHeroes.await().get(mostPlayedHero.id)
            val winsLosses = winsLossesTask.await()

            PlayerInfoEntity(
                nickname = mainInfo.profile.nickname,
                avatarUrl = mainInfo.profile.avatarUrl,
                lastOnline = fetchDate(mainInfo.profile.lastLogin),
                hasDotaPlus = mainInfo.profile.hasDotaPlus,
                mmr = mainInfo.mmr.estimate ?: 0,
                wins = winsLosses.wins,
                losses = winsLosses.losses,
                winRate = winsLosses.winRate,
                mostPlayedHeroName = mostPlayedHeroInfo?.name ?: "none",
                mostPlayedHeroImageUrl = buildImageResUrl(mostPlayedHeroInfo),
                profileLink = buildProfileLink(mainInfo),
                steamProfileLink = mainInfo.profile.steamProfileLink
            )
        }

    private fun buildProfileLink(mainInfo: PlayerInfoDto) =
        "$PROFILE_HOST${mainInfo.profile.id}"

    private fun buildImageResUrl(mostPlayedHeroInfo: HeroInfo?) =
        "$RES_HOST${mostPlayedHeroInfo?.avatarSuffix}"

    private fun getDateDiff(date: Date): String {
        var msDiff = Date().time - date.time

        // 1ms 1000s 60_000m 3_600_000h 86_400_000d
        val days = msDiff / 86_400_000L
        msDiff -= days
        val hours = msDiff / 3_600_000L
        msDiff -= hours
        val mins = msDiff / 60_000L
        msDiff -= mins
        val seconds = msDiff / 1000L

        val leadingMeasure =
            if (days != 0L) "$days days"
            else if (hours != 0L) "$hours hours"
            else if (mins != 0L) "$mins mins"
            else if (seconds != 0L) "$seconds mins"
            else NONE

        return if (leadingMeasure != NONE) "$leadingMeasure ago" else "just now"
    }

    private fun fetchDate(lastLogin: String?): String {
        if (lastLogin == null) return "no date provided"
        // date format : yyyy-MM-dd’T’HH:mm:ss.SSS’Z’
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val date = formatter.parse(lastLogin)
        return getDateDiff(date)
    }

    override suspend fun getPlayerInfoShort(playerId: String): PlayerInfoShortEntity =
        withContext(Dispatchers.IO) {
            NetworkService.serverAPI
                .getPlayerInfo(playerId)
                .let {
                    PlayerInfoShortEntity(
                        nickname = it.profile.nickname,
                        avatarUrl = it.profile.avatarUrl,
                        lastOnline = fetchDate(it.profile.lastLogin),
                        hasDotaPlus = it.profile.hasDotaPlus,
                        profileLink = buildProfileLink(it),
                        steamProfileLink = it.profile.steamProfileLink,
                    )
                }
        }
}