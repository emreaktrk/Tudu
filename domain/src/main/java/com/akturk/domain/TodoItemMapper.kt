package com.akturk.domain

import com.akturk.common.Mapper
import com.akturk.data.model.TodoEntity
import com.akturk.domain.model.TodoItem


internal class TodoItemMapper : Mapper<TodoEntity, TodoItem> {

    /**
     * Elvis operator must be unnecessary but i want to show its a good way to handle nullables
     */
    override fun transform(item: TodoEntity): TodoItem {
        return TodoItem(
            item.title,
            item.description,
//            item.tags,
            setLatLng(item.lat, item.lng)
        )
    }

    internal fun setLatLng(lat: Double?, lng: Double?): Pair<Double, Double>? {
        return if (lat != null && lng != null) {
            Pair(lat, lng)
        } else null
    }
}