package com.akturk.data

import android.content.Context
import androidx.room.Room
import com.akturk.data.db.TodoDao
import com.akturk.data.db.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room
            .databaseBuilder(context, TodoDatabase::class.java, "TODO_DB")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideDao(db: TodoDatabase): TodoDao {
        return db.todoDao()
    }

    /**
     * Qualifiers can be used if more sources required.
     * @see <a href="https://developer.android.com/training/dependency-injection/hilt-android#multiple-bindings">Provide multiple bindings for the same type</a>
     */
    @Provides
    fun provideTodoSource(dao: TodoDao): ITodoSource {
        return LocalTodoSource(dao)
    }

    @Provides
    fun provideTodoRepository(source: ITodoSource): ITodoRepository {
        return TodoRepository(source)
    }
}