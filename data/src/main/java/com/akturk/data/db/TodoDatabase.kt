package com.akturk.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoEntity::class, TagEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}