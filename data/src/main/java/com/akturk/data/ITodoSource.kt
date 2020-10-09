package com.akturk.data

import androidx.annotation.IntRange
import com.akturk.data.model.TodoEntity
import kotlinx.coroutines.flow.Flow

interface ITodoSource : ISource {
    fun items(): Flow<List<TodoEntity>>
    fun insert(item: TodoEntity)
    fun random(): TodoEntity
    fun populate(@IntRange(from = 0) iteration: Int)
    fun filter(search: String): Flow<List<TodoEntity>>
}