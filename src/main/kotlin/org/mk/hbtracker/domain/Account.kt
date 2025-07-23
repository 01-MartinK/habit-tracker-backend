package org.mk.hbtracker.domain

import jakarta.persistence.*

@Entity
@Table(name = "accounts")
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val email: String,
    val password: String,
    val lastLogin: String,
    val userId: Long,
)