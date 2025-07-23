package org.mk.hbtracker.domain

data class CreateHabitRequest(
    val name: String,
    val startTime: Int,
    val duration: Int,
)

data class UpdateHabitRequest(
    val name: String,
    val startTime: Int,
    val duration: Int,
)

data class CreateUserRequest(
    val username: String,
)

data class UpdateUserRequest(
    val username: String
)