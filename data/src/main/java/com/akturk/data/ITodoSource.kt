package com.akturk.data

import androidx.annotation.IntRange
import com.akturk.data.model.TodoWithTags

interface ITodoSource : ISource {
    fun items(): List<TodoWithTags>
    fun insert(item: TodoWithTags)
    fun random(): TodoWithTags
    fun populate(@IntRange(from = 0) iteration: Int)
    fun filter(search: String): List<TodoWithTags>
}