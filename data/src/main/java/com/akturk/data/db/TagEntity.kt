package com.akturk.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "tags",
    foreignKeys = [
        ForeignKey(
            entity = TodoEntity::class,
            parentColumns = [("id")],
            childColumns = [("todo_id")],
            onDelete = CASCADE
        )]
)
data class TagEntity(
    @ColumnInfo(name = "value")
    val value: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ForeignKey(
        entity = TodoEntity::class,
        parentColumns = ["id"],
        childColumns = [("todo_id")],
        onDelete = CASCADE,
    )
    @ColumnInfo(name = "todo_id")
    var todoId: Long? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TagEntity

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}