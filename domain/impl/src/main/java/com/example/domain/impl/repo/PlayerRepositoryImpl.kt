package com.example.domain.impl.repo

import com.example.domain.impl.db.DatabaseService
import com.example.domain.impl.db.entity.HeroEntity
import com.example.domain.impl.net.dto.HeroInfoDto
import com.example.domain.impl.net.dto.PlayerInfoDto
import com.example.domain.impl.net.NetworkService
import com.expample.domain.api.entities.PlayerEntity
import com.expample.domain.api.entities.PlayerInfoEntity
import com.expample.domain.api.entities.PlayerInfoShortEntity
import com.expample.domain.api.repo.PlayerRepository
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

    override suspend fun fetchHeroes(): Unit = withContext(Dispatchers.IO) {
        NetworkService.serverAPI
            .getHeroes()
            .values.map {
                HeroEntity(
                    id = it.id,
                    name = it.keyName,
                    localName = it.name,
                    primaryAttr = it.primaryAttr,
                    attackType = it.attackType,
                    roles = it.roles.joinToString(),
                    imgUrl = buildImageResUrl(it.imageSuffix),
                    iconUrl = buildImageResUrl(it.iconSuffix),
                    baseHealth = it.baseHealth,
                    baseHealthRegen = it.baseHealthRegen,
                    baseMana = it.baseMana,
                    baseManaRegen = it.baseManaRegen,
                    baseArmor = it.baseArmor,
                    baseMr = it.baseMr,
                    baseAttackMin = it.baseAttackMin,
                    baseAttackMax = it.baseAttackMax,
                    baseStr = it.baseStr,
                    baseAgi = it.baseAgi,
                    baseInt = it.baseInt,
                    strGain = it.strGain,
                    agiGain = it.agiGain,
                    intGain = it.intGain,
                    attackRange = it.attackRange,
                    projectileSpeed = it.projectileSpeed,
                    attackRate = it.attackRate,
                    baseAttackTime = it.baseAttackTime,
                    attackPoint = it.attackPoint,
                    moveSpeed = it.moveSpeed,
                    legs = it.legs,
                    dayVision = it.dayVision,
                    nightVision = it.nightVision,
                )
            }
            .also {
                DatabaseService.db
                    .heroDao()
                    .insertAll(it)
            }
    }

    private fun buildImageResUrl(suffix: String) = "$RES_HOST${suffix}"

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
            val allHeroesTask = async(SupervisorJob()) {
                DatabaseService.db.heroDao().getAll()
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
            val mostPlayedHeroInfo = allHeroesTask.await().get(mostPlayedHero.id)
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
                mostPlayedHeroName = mostPlayedHeroInfo.localName,
                mostPlayedHeroImageUrl = mostPlayedHeroInfo.imgUrl,
                profileLink = buildProfileLink(mainInfo),
                steamProfileLink = mainInfo.profile.steamProfileLink
            )
        }

    private fun buildProfileLink(mainInfo: PlayerInfoDto) =
        "$PROFILE_HOST${mainInfo.profile.id}"

    private fun fetchDate(lastLogin: String?): String {
        if (lastLogin == null) return "no date provided"

        // date format : yyyy-MM-dd’T’HH:mm:ss.SSS’Z’
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val date = formatter.parse(lastLogin)
        return getDateDiff(date)
    }

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