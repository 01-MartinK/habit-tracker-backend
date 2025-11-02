package com.habit.domain

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Streak (
    val id: UUID,
    var completedHabits: List<String>,
)