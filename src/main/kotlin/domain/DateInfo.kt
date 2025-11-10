package com.habit.domain

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class DateInfo (
    val id: UUID,
    var completedHabits: List<String>,
    var steps: Number = 0,
)