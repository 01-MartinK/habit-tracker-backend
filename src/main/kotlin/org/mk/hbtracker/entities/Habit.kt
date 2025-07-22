package org.mk.hbtracker.entities

import jakarta.persistence.Entity
import kotlinx.datetime.LocalDateTime

@Entity
data class Habit (
 val name: String,
 val startTime: Int,
 val duration: Int,
 val createdAt: LocalDateTime,
 val modifiedAt: LocalDateTime,
 val userId: String,
)