package com.example.domain.impl.net.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroInfoDto(
    val id: Int,
    @SerialName("name")
    val keyName: String,
    @SerialName("localized_name")
    val name: String,
    @SerialName("primary_attr")
    val primaryAttr: String,
    @SerialName("attack_type")
    val attackType: String,
    @SerialName("roles")
    val roles: List<String>,
    @SerialName("img")
    val imageSuffix: String,
    @SerialName("icon")
    val iconSuffix: String,
    @SerialName("base_health")
    val baseHealth: Int,
    @SerialName("base_health_regen")
    val baseHealthRegen: Float,
    @SerialName("base_mana")
    val baseMana: Int,
    @SerialName("base_mana_regen")
    val baseManaRegen: Float,
    @SerialName("base_armor")
    val baseArmor: Float,
    @SerialName("base_mr")
    val baseMr: Int,
    @SerialName("base_attack_min")
    val baseAttackMin: Int,
    @SerialName("base_attack_max")
    val baseAttackMax: Int,
    @SerialName("base_str")
    val baseStr: Int,
    @SerialName("base_agi")
    val baseAgi: Int,
    @SerialName("base_int")
    val baseInt: Int,
    @SerialName("str_gain")
    val strGain: Float,
    @SerialName("agi_gain")
    val agiGain: Float,
    @SerialName("int_gain")
    val intGain: Float,
    @SerialName("attack_range")
    val attackRange: Int,
    @SerialName("projectile_speed")
    val projectileSpeed: Int,
    @SerialName("attack_rate")
    val attackRate: Float,
    @SerialName("base_attack_time")
    val baseAttackTime: Int,
    @SerialName("attack_point")
    val attackPoint: Float,
    @SerialName("move_speed")
    val moveSpeed: Int,
    @SerialName("legs")
    val legs: Int,
    @SerialName("day_vision")
    val dayVision: Int,
    @SerialName("night_vision")
    val nightVision: Int,
)
