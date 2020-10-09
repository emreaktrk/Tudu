package com.akturk.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.akturk.data.model.TodoEntity

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}