package com.akturk.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.akturk.data.db.TagEntity
import com.akturk.data.db.TodoEntity

data class TodoWithTags(

    @Embedded
    val todo: TodoEntity,

    @Relation(parentColumn = "id", entityColumn = "todo_id", entity = TagEntity::class)
    val tags: List<TagEntity>,
)