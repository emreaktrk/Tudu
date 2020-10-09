package com.akturk.data

import com.akturk.data.model.TodoEntity
import kotlinx.coroutines.flow.Flow
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils

class LocalTodoSource(private val dao: TodoDao) : ITodoSource {

    override fun items(): Flow<List<TodoEntity>> {
        return dao.items()
    }

    override fun insert(item: TodoEntity) {
        return dao.insert(item)
    }

    override fun random(): TodoEntity {
        return TodoEntity(
            title = RandomStringUtils.random(10, "abcdefghijklmnopqrstuvwxyz "),
            description = RandomStringUtils.random(50, "abcdefghijklmnopqrstuvwxyz "),
//            tags = mutableSetOf<Tag>().apply {
//                val iteration = RandomUtils.nextInt(0, 5)
//                for (i in 0 until iteration) {
//                    val word = RandomStringUtils.random(3)
//                    add(word)
//                }
//            },
            lat = RandomUtils.nextDouble(),
            lng = RandomUtils.nextDouble()
        )
    }

    override fun populate(iteration: Int) {
        for (i in 0..iteration) {
            dao.insert(random())
        }
    }

    override fun filter(search: String): Flow<List<TodoEntity>> {
        return dao.filter(search)
    }
}