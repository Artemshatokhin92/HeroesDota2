package com.shatokhin.heroesdota2.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shatokhin.heroesdota2.data.repository.RepositoryImpl
import com.shatokhin.heroesdota2.domain.usecase.UseCaseGetListAllHeroes

class ViewModelListAllHeroesFactory: ViewModelProvider.Factory {
    private val repositoryImpl = RepositoryImpl()

    private val useCaseGetListAllHeroes = UseCaseGetListAllHeroes(repositoryImpl)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelListAllHeroes(useCaseGetListAllHeroes) as T
    }

}