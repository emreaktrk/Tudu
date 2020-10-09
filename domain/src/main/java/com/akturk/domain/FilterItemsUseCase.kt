package com.akturk.domain

import com.akturk.data.ITodoRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FilterItemsUseCase @Inject constructor(private val repo: ITodoRepository) :
    IUseCase<() -> Unit> {

    lateinit var search: String

    override suspend fun invoke(delegate: () -> Unit) {
        with(TodoItemMapper()) {
            repo.filter(search).map {
                return@map it.map(::transform)
            }
        }
    }
}