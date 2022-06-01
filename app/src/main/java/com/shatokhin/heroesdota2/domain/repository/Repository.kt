package com.shatokhin.heroesdota2.domain.repository

import com.shatokhin.heroesdota2.data.models.HeroJson

interface Repository {
    suspend fun getListAllHeroes(): List<HeroJson>
    fun getCharacteristicsHero()
}