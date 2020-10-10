package com.akturk.data

import androidx.annotation.IntRange
import com.akturk.data.db.TodoEntity
import com.akturk.data.model.TodoWithTags
import kotlinx.coroutines.flow.Flow

interface ITodoRepository : IRepository {
    fun items(): Flow<List<TodoWithTags>>
    fun insert(item: TodoWithTags)
    fun populate(@IntRange(from = 0) iteration: Int)
    fun filter(search: String): Flow<List<TodoWithTags>>
}