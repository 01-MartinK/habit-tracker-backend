package com.habit.domain

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat

@Serializable
data class Habit(
    val id: Long,
    val icon: String,
    val title: String,
    val description: String,
    val createdAt: SimpleDateFormat,
    val modifiedAt: SimpleDateFormat,
)