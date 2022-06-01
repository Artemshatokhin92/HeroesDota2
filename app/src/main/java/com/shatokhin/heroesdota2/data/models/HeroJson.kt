package com.shatokhin.heroesdota2.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroJson(
    @Json(name = "id")
    val idItemJson: Int,
    @Json(name = "localized_name")
    val name: String,
    @Json(name = "primary_attr")
    val primaryAttr: String,
    @Json(name = "attack_type")
    val attackType: String,
    @Json(name = "roles")
    val roles: List<String>,
    @Json(name = "img")
    val img: String,
    @Json(name = "base_health")
    val baseHealth: Int,
    @Json(name = "base_mana")
    val baseMana: Int,
    @Json(name = "base_armor")
    val baseArmor: Double,
    @Json(name = "base_str")
    val baseStrength: Int,
    @Json(name = "base_agi")
    val baseAgility: Int,
    @Json(name = "base_int")
    val baseIntellect: Int,
    @Json(name = "hero_id")
    val heroId: Int
)