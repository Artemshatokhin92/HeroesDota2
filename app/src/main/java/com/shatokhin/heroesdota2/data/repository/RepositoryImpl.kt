package com.shatokhin.heroesdota2.data.repository

import android.util.Log
import com.shatokhin.heroesdota2.data.models.HeroJson
import com.shatokhin.heroesdota2.domain.repository.Repository
import com.shatokhin.heroesdota2.utilites.TAG_FOR_LOGCAT
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.delay
import okhttp3.*
import java.io.IOException

class RepositoryImpl: Repository {

    override suspend fun getListAllHeroes(): List<HeroJson> {
        var listAllHeroes = listOf<HeroJson>()

        val fullUrl = "https://api.opendota.com/api/heroStats"
        val okHttpClient = OkHttpClient()
        val request: Request = Request.Builder()
            .url(fullUrl)
            .build()

        var attempts = 5 // количество попыток дождаться ответ с сервера
        val waitingTimeMillis = 1000L // время ожидания 1сек (1 попытка дождаться = 1 сек)
        var callCompleted = false // обработан ли вызов с интернета, если нет, ждем attempts количество раз
//        okHttpClient.newCall(request).
        okHttpClient.newCall(request).enqueue(object: Callback {

            override fun onFailure(call: Call, e: IOException) {
                callCompleted = true
                Log.d(TAG_FOR_LOGCAT, "onFailure")
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()

                json?.let {
                    Log.d(TAG_FOR_LOGCAT, it)

                    val moshi = Moshi.Builder().build()
                    val listType = Types.newParameterizedType(List::class.java, HeroJson::class.java)
                    val adapter: JsonAdapter<List<HeroJson>> = moshi.adapter(listType)

                    listAllHeroes = adapter.fromJson(json)!!
                }
                callCompleted = true
            }
        })

        // если ответ с сервера еще в обработке, ожидаем 1 сек и снова проверяем, всего ожидаем 5 сек
        while (!callCompleted && attempts >= 0) {
            attempts-- // всего 5 попыток дождаться ответ, перед следующим ожиданием уменьшаем оставшиеся кол-во попыток
            delay(waitingTimeMillis) // ожидание еще 1 сек
        }

        return listAllHeroes
    }


    override fun getCharacteristicsHero() {

    }
}