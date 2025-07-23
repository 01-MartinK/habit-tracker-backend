package org.mk.hbtracker.domain

data class CreateHabitRequest(
    val name: String,
    val startTime: Int,
    val duration: Int,
)

data class UpdateHabitRequest(
    val id: Long,
    val name: String,
    val startTime: Int,
    val duration: Int,
)