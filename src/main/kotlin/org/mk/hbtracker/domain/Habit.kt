package org.mk.hbtracker.domain

import java.util.Date

data class Habit (
 val name: String,
 val startTime: Number,
 val duration: Number,
 val createdAt: Date,
 val modifiedAt: Date,
 val userId: String,
)