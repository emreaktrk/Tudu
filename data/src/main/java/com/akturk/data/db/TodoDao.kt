package com.akturk.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.akturk.data.model.TodoWithTags
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun items(): Flow<List<TodoWithTags>>

    @Query("SELECT * FROM todo LEFT JOIN tags ON todo.id = tags.todo_id WHERE todo.title LIKE '%'||:keyword||'%'")
    fun filter(keyword: String): Flow<List<TodoWithTags>>

    fun insert(item: TodoWithTags) {
        val todoId = insert(item.todo)
        item.tags.forEach {
            it.todoId = todoId
            insert(it)
        }
    }

    @Insert
    fun insert(entity: TodoEntity): Long

    @Insert
    fun insert(entity: TagEntity): Long
}