package com.habit.domain

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import java.util.*

object DateInfos: Table() {
    val id = uuid("id")
    val completedHabits = text("completed_habits")
    val steps = integer("steps")
}

@Serializable
data class DateInfo (
    val id: UUID,
    var completedHabits: List<String>,
    var steps: Number = 0,
)