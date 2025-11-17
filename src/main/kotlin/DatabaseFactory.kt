package com.habit

import com.habit.domain.DateInfos
import com.habit.domain.Habits
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.util.*

object DatabaseFactory {
    fun init() {
        Database.connect(hikari())
        transaction {
            create(Habits)
            create(DateInfos)
            Habits.insert {
                it[icon] = "person-running"
                it[title] = "Workout"
                it[description] = "6:00 - 2h"
                it[createdAt] = LocalDateTime.now().toString()
                it[modifiedAt] = LocalDateTime.now().toString()
            }
            Habits.insert {
                it[icon] = "moon"
                it[title] = "Sleep"
                it[description] = "22:00 - 6:00"
                it[createdAt] = LocalDateTime.now().toString()
                it[modifiedAt] = LocalDateTime.now().toString()
            }
            Habits.insert {
                it[icon] = "person-praying"
                it[title] = "Yoga"
                it[description] = "16:00 - 1h"
                it[createdAt] = LocalDateTime.now().toString()
                it[modifiedAt] = LocalDateTime.now().toString()
            }
            Habits.insert {
                it[icon] = "person-running"
                it[title] = "Moouth exercises"
                it[description] = "8:00 - 10min"
                it[createdAt] = LocalDateTime.now().toString()
                it[modifiedAt] = LocalDateTime.now().toString()
            }
            DateInfos.insert {
                it[id] = UUID.nameUUIDFromBytes("17-11-2025".toByteArray())
                it[completedHabits] = "2,3"
                it[steps] = 15600
            }
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}