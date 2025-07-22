package org.mk.hbtracker.entities

import jakarta.persistence.Entity
import kotlinx.datetime.LocalDateTime

@Entity
data class User(
    val id: String,
    val email: String,
    val username: String,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime,
)