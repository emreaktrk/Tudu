package com.akturk.domain

import com.akturk.data.ITodoRepository
import com.akturk.domain.model.TodoItem
import javax.inject.Inject

class AddItemUseCase @Inject constructor(private val repo: ITodoRepository) :
    IUseCase<() -> TodoItem> {

    override suspend fun invoke(delegate: () -> TodoItem) {
        with(TodoEntityMapper()) {
            val item = transform(delegate())
            repo.insert(item)
        }
    }
}