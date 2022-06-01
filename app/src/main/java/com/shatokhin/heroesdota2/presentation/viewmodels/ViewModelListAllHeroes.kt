package com.shatokhin.heroesdota2.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shatokhin.heroesdota2.data.models.HeroJson
import com.shatokhin.heroesdota2.domain.usecase.UseCaseGetListAllHeroes
import kotlinx.coroutines.launch

class ViewModelListAllHeroes(
    private val useCaseGetListAllHeroes: UseCaseGetListAllHeroes,
) : ViewModel() {

    private val mListHeroJson = MutableLiveData<List<HeroJson>>()
    val listHeroJson: LiveData<List<HeroJson>>
        get() = mListHeroJson

    fun loadListAllHeroes() {
        viewModelScope.launch {
            mListHeroJson.value = useCaseGetListAllHeroes.execute()
        }
    }

}