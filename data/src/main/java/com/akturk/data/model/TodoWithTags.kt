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
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TodoWithTags

        if (todo != other.todo) return false

        return true
    }

    override fun hashCode(): Int {
        return todo.hashCode()
    }
}