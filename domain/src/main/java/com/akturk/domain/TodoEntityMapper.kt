package com.akturk.domain

import com.akturk.common.Mapper
import com.akturk.data.model.TodoEntity
import com.akturk.domain.model.TodoItem

internal class TodoEntityMapper : Mapper<TodoItem, TodoEntity> {
    override fun transform(item: TodoItem): TodoEntity {
        return TodoEntity(
            item.title,
            item.description,
            item.latLng?.first,
            item.latLng?.second
        )
    }
}