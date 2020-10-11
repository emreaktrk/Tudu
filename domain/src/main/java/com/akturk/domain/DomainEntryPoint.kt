package com.akturk.domain

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface DomainEntryPoint {
    fun getPopulateUseCase(): PopulateItemsUseCase
    fun getItemsUseCase(): GetItemsUseCase
    fun getFilterUseCase(): FilterItemsUseCase
}