package com.akturk.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * It could be data-class but i want to show type-safe builders aka dsl
 */
@Entity(tableName = "todo")
data class TodoEntity(

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "lat")
    var lat: Double?,

    @ColumnInfo(name = "lng")
    var lng: Double?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}
