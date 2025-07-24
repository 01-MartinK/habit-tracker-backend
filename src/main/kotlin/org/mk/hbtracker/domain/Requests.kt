package org.mk.hbtracker.domain

data class CreateHabitRequest(
    val name: String,
    val startTime: Int,
    val duration: Int,
    val userId: Long
)

data class UpdateHabitRequest(
    val name: String,
    val startTime: Int,
    val duration: Int,
    val userId: Long,
)

data class CreateUserRequest(
    val username: String,
)

data class UpdateUserRequest(
    val username: String
)

data class CreateAccountRequest(
    val username: String,
    val email: String,
    val password: String
)