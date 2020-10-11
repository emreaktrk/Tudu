package com.akturk.domain

import com.akturk.data.ITodoRepository
import com.akturk.domain.model.TodoItem
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

class FilterItemsUseCase @Inject constructor(private val repo: ITodoRepository) :
    IUseCase<(result: List<TodoItem>) -> Unit> {

    var search = ""

    @FlowPreview
    override suspend fun invoke(delegate: (result: List<TodoItem>) -> Unit) {
        with(TodoItemMapper()) {
            val result = repo.filter(search).map(::transform)
            delegate(result)
        }
    }
}