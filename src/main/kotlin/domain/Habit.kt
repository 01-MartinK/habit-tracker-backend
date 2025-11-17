package com.habit.domain

import com.habit.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Habits : Table() {
    val id = integer("id").autoIncrement()
    val icon = varchar("icon", 100)
    val title = varchar("title", 160)
    val description = varchar("description", 255)
    val createdAt = varchar("created_at", 64)
    val modifiedAt = varchar("modified_at", 64)
    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Habit(
    @Serializable(with = UUIDSerializer::class)
    val id: Int,
    val icon: String,
    val title: String,
    val description: String,
    val createdAt: String,
    val modifiedAt: String,
)

