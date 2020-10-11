package com.akturk.domain

import com.akturk.data.ITodoRepository
import com.akturk.domain.model.TodoItem
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val repo: ITodoRepository) :
    IUseCase<(result: List<TodoItem>) -> Unit> {

    @FlowPreview
    override suspend fun invoke(delegate: (result: List<TodoItem>) -> Unit) {
        with(TodoItemMapper()) {
            val items = repo.items().map(::transform)
            delegate(items)
        }
    }
}