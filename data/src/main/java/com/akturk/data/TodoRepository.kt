package com.akturk.data

import com.akturk.data.model.TodoWithTags
import kotlinx.coroutines.flow.Flow


class TodoRepository(private val source: ITodoSource) : ITodoRepository {
    override fun items(): Flow<List<TodoWithTags>> = source.items()
    override fun insert(item: TodoWithTags) = source.insert(item)
    override fun populate(iteration: Int) = source.populate(iteration)
    override fun filter(search: String) = source.filter(search)
}