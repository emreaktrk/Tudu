package com.akturk.domain

import com.akturk.common.Mapper
import com.akturk.data.db.TagEntity
import com.akturk.data.db.TodoEntity
import com.akturk.data.model.TodoWithTags
import com.akturk.domain.model.TodoItem

internal class TodoEntityMapper : Mapper<TodoItem, TodoWithTags> {

    override fun transform(item: TodoItem): TodoWithTags {
        return TodoWithTags(
            todo = TodoEntity(
                item.title,
                item.description,
                item.latLng?.first,
                item.latLng?.second,
            ),
            tags = item.tags.map { TagEntity(it) }
        )
    }

}