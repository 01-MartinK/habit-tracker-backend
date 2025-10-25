package com.habit.domain

import com.habit.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Habit(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val icon: String,
    val title: String,
    val description: String,
    val createdAt: String,
    val modifiedAt: String,
)

