package org.mk.hbtracker.domain

import jakarta.persistence.*

@Entity
@Table(name = "habits")
data class Habit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var startTime: Int,
    var duration: Int,
    var createdAt: String,
    var modifiedAt: String,
    var userId: Long,
)