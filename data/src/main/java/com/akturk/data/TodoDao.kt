package com.akturk.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akturk.data.model.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: TodoEntity)

    @Query("SELECT * FROM todo")
    fun items(): Flow<List<TodoEntity>>

    @Query("SELECT * FROM todo WHERE title LIKE '%'||:keyword||'%'")
    fun filter(keyword: String): Flow<List<TodoEntity>>
}