package com.habit.domain

import kotlinx.serialization.Serializable

@Serializable
data class CreateHabitAction(
    val icon: String,
    val title: String,
    val description: String,
)

@Serializable
data class UpdateHabitAction(
    val icon: String,
    val title: String,
    val description: String
)