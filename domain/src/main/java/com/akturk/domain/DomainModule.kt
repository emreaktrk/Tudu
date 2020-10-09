package com.akturk.domain

import com.akturk.data.ITodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DomainModule {

    @Provides
    fun providePopulateUseCase(repo: ITodoRepository): PopulateItemsUseCase {
        return PopulateItemsUseCase(repo)
    }

    @Provides
    fun provideGetItemsUseCase(repo: ITodoRepository): GetItemsUseCase {
        return GetItemsUseCase(repo)
    }
}