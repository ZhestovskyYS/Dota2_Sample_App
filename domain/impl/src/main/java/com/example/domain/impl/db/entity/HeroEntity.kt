package com.example.domain.impl.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero")
data class HeroEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "localized_name")
    val localName: String,
    @ColumnInfo(name = "primary_attr")
    val primaryAttr: String,
    @ColumnInfo(name = "attack_type")
    val attackType: String,
    @ColumnInfo(name = "roles")
    val roles: String,
    @ColumnInfo(name = "imgUrl")
    val imgUrl: String,
    @ColumnInfo(name = "iconUrl")
    val iconUrl: String,
    @ColumnInfo(name = "base_health")
    val baseHealth: Int,
    @ColumnInfo(name = "base_health_regen")
    val baseHealthRegen: Float,
    @ColumnInfo(name = "base_mana")
    val baseMana: Int,
    @ColumnInfo(name = "base_mana_regen")
    val baseManaRegen: Float,
    @ColumnInfo(name = "base_armor")
    val baseArmor: Float,
    @ColumnInfo(name = "base_mr")
    val baseMr: Int,
    @ColumnInfo(name = "base_attack_min")
    val baseAttackMin: Int,
    @ColumnInfo(name = "base_attack_max")
    val baseAttackMax: Int,
    @ColumnInfo(name = "base_str")
    val baseStr: Int,
    @ColumnInfo(name = "base_agi")
    val baseAgi: Int,
    @ColumnInfo(name = "base_int")
    val baseInt: Int,
    @ColumnInfo(name = "str_gain")
    val strGain: Float,
    @ColumnInfo(name = "agi_gain")
    val agiGain: Float,
    @ColumnInfo(name = "int_gain")
    val intGain: Float,
    @ColumnInfo(name = "attack_range")
    val attackRange: Int,
    @ColumnInfo(name = "projectile_speed")
    val projectileSpeed: Int,
    @ColumnInfo(name = "attack_rate")
    val attackRate: Float,
    @ColumnInfo(name = "base_attack_time")
    val baseAttackTime: Int,
    @ColumnInfo(name = "attack_point")
    val attackPoint: Float,
    @ColumnInfo(name = "move_speed")
    val moveSpeed: Int,
    @ColumnInfo(name = "legs")
    val legs: Int,
    @ColumnInfo(name = "day_vision")
    val dayVision: Int,
    @ColumnInfo(name = "night_vision")
    val nightVision: Int,
)