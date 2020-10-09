package com.akturk.domain

import com.akturk.data.ITodoRepository
import com.akturk.domain.model.TodoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val repo: ITodoRepository) :
    IUseCase<(result: Flow<List<TodoItem>>) -> Unit> {

    override suspend fun invoke(delegate: (result: Flow<List<TodoItem>>) -> Unit) {
        with(TodoItemMapper()) {
            val items = repo.items().map {
                return@map it.map(::transform)
            }

            delegate(items)
        }
    }
}