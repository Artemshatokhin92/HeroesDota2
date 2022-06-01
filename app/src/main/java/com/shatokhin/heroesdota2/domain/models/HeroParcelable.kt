package com.shatokhin.heroesdota2.domain.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class HeroParcelable(
    val name: String,
    val primaryAttr: String,
    val attackType: String,
    val roles: List<String>,
    val img: String,
    val baseHealth: Int,
    val baseMana: Int,
    val baseArmor: Double,
    val baseStrength: Int,
    val baseAgility: Int,
    val baseIntellect: Int
    ): Parcelable