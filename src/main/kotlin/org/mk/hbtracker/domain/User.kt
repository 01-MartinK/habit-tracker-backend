package org.mk.hbtracker.domain

import java.util.Date

data class User(
    val id: String,
    val email: String,
    val username: String,
    val createdAt: Date,
    val deletedAt: Date,
)