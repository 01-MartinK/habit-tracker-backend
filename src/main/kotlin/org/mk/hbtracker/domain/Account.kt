package org.mk.hbtracker.domain

import java.util.Date

data class Account(
    val email: String,
    val password: String,
    val lastLogin: Date,
    val user: User,
)