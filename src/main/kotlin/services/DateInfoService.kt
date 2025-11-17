package com.habit.services

import com.habit.domain.DateInfo
import com.habit.domain.DateInfos
import com.habit.model.DateInfoRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.*

class DateInfoService: DateInfoRepository {
    private fun toDateInfo(row: ResultRow): DateInfo =
        DateInfo(
            id = row[DateInfos.id],
            completedHabits = row[DateInfos.completedHabits].split(","),
            steps = row[DateInfos.steps]
        )

    override suspend fun findAll(): List<DateInfo> = newSuspendedTransaction {
        DateInfos.selectAll().map { toDateInfo(it) }
    }

    override suspend fun findByDate(date: String): DateInfo? =
        findAll().find { it.id == UUID.nameUUIDFromBytes(date.toByteArray()) }

    override suspend fun create(date: String): UUID = newSuspendedTransaction {
        val newId = UUID.nameUUIDFromBytes(date.toByteArray())
        DateInfos.insert {
            it[id] = newId
            it[completedHabits] = ""
            it[steps] = 0
        }

        newId
    }

    override suspend fun update(
        date: String,
        newHabits: List<String>
    ): Int = newSuspendedTransaction {
        DateInfos.update({ DateInfos.id eq UUID.nameUUIDFromBytes(date.toByteArray())}) {
            it[completedHabits] = newHabits.joinToString(separator = ",")
            it[steps] = 0
        }
    }

    override suspend fun delete(date: String): Int = newSuspendedTransaction {
        DateInfos.deleteWhere { DateInfos.id eq UUID.nameUUIDFromBytes(date.toByteArray()) }
    }
}