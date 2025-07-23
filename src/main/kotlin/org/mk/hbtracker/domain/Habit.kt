package org.mk.hbtracker.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "habits")
data class Habit(
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 var id: Long? = null,
 var name: String,
 var startTime: Int,
 var duration: Int,
 var createdAt: LocalDateTime,
 var modifiedAt: LocalDateTime,
 var userId: String? = null,
)