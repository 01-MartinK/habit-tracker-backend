package com.habit.domain

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.UUID

@Serializable
data class Habit(
    val id: UUID,
    val icon: String,
    val title: String,
    val description: String,
    val createdAt: SimpleDateFormat,
    val modifiedAt: SimpleDateFormat,
)

