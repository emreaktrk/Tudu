package com.akturk.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.akturk.data.model.TodoWithTags

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun items(): List<TodoWithTags>

    @Query("SELECT DISTINCT todo.* FROM todo LEFT JOIN tags ON todo.id = tags.todo_id WHERE todo.title LIKE '%'||:search||'%' or todo.description LIKE '%'||:search||'%' or tags.value LIKE '%'||:search")
    fun filter(search: String): List<TodoWithTags>

    @Transaction
    fun insert(item: TodoWithTags) {
        val todoId = insert(item.todo)
        item.tags.forEach {
            it.todoId = todoId
            insert(it)
        }
    }

    @Insert
    @Transaction
    fun insert(entity: TodoEntity): Long

    @Insert
    @Transaction
    fun insert(entity: TagEntity): Long
}