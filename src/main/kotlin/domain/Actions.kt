package com.habit.domain

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class CreateHabitAction(
    val id: UUID? = null,
    val icon: String,
    val title: String,
    val description: String,
    )

@Serializable
data class UpdateDateInfoAction(
    val habits: List<String>
)