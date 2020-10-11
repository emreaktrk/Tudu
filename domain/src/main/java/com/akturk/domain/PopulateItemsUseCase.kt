package com.akturk.domain

import com.akturk.data.ITodoRepository
import kotlin.properties.Delegates

class PopulateItemsUseCase(private val repo: ITodoRepository) : IUseCase<() -> Unit> {

    var iteration by Delegates.notNull<Int>()

    override suspend fun invoke(delegate: () -> Unit) {
        repo.populate(iteration)
        delegate()
    }
}