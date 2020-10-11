package com.akturk.data

import com.akturk.data.db.TagEntity
import com.akturk.data.db.TodoDao
import com.akturk.data.db.TodoEntity
import com.akturk.data.model.TodoWithTags
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils

class LocalTodoSource(private val dao: TodoDao) : ITodoSource {

    override fun items(): List<TodoWithTags> {
        return dao.items()
    }

    override fun insert(item: TodoWithTags) {
        return dao.insert(item)
    }

    override fun random(): TodoWithTags {
        return TodoWithTags(
            todo = TodoEntity(
                title = RandomStringUtils.random(10, "abcdefghijklmnopqrstuvwxyz "),
                description = RandomStringUtils.random(50, "abcdefghijklmnopqrstuvwxyz "),

                lat = RandomUtils.nextDouble(),
                lng = RandomUtils.nextDouble()
            ),
            tags = mutableListOf<TagEntity>().apply {
                val count = RandomUtils.nextInt(0, 10)
                for (index in 0..count) {
                    val tag = TagEntity(RandomStringUtils.random(8, "abcdefghijklmnopqrstuvwxyz "))
                    add(tag)
                }
            }
        )
    }

    override fun populate(iteration: Int) {
        for (i in 0..iteration) {
            dao.insert(random())
        }
    }

    override fun filter(search: String): List<TodoWithTags> {
        return dao.filter(search)
    }
}