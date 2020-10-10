package com.akturk.domain

import com.akturk.common.Mapper
import com.akturk.data.model.TodoWithTags
import com.akturk.domain.model.TodoItem


internal class TodoItemMapper : Mapper<TodoWithTags, TodoItem> {

    /**
     * Elvis operator must be unnecessary but i want to show its a good way to handle nullables
     */
    override fun transform(item: TodoWithTags): TodoItem {
        return TodoItem(
            item.todo.title,
            item.todo.description,
            setLatLng(item.todo.lat, item.todo.lng),
            tags = item.tags.map { it.value }.toMutableSet()
        )
    }

    private fun setLatLng(lat: Double?, lng: Double?): Pair<Double, Double>? {
        return if (lat != null && lng != null) {
            Pair(lat, lng)
        } else null
    }
}