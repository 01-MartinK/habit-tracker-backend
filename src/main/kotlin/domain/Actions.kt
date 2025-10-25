package com.habit.domain

import io.ktor.http.Parameters
import kotlinx.serialization.Serializable

@Serializable
data class CreateHabitAction(
    val icon: String,
    val title: String,
    val description: String,
    )

fun Parameters.toCreateAction(): CreateHabitAction? {
    if (this["icon"].isNullOrEmpty() || this["title"].isNullOrEmpty() || this["description"].isNullOrEmpty()) return null

    return CreateHabitAction(
        icon = this["icon"].toString(),
        title = this["title"].toString(),
        description = this["description"].toString()
    )
}