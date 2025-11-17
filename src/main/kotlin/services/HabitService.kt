package com.habit.services

import com.habit.domain.CreateHabitAction
import com.habit.domain.Habit
import com.habit.domain.Habits
import com.habit.model.HabitRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.time.LocalDateTime

class HabitService : HabitRepository {
    private fun toHabit(row: ResultRow): Habit =
        Habit(
            id = row[Habits.id],
            icon = row[Habits.icon],
            title = row[Habits.title],
            description = row[Habits.description],
            createdAt = row[Habits.createdAt],
            modifiedAt = row[Habits.modifiedAt]
        )

    override suspend fun findAll(): List<Habit> = newSuspendedTransaction {
        Habits.selectAll().map { toHabit(it) }
    }


    override suspend fun findById(id: Int): Habit? =
        findAll().find { it.id == id }

    override suspend fun create(action: CreateHabitAction): Int = newSuspendedTransaction {
        Habits.insertReturning {
            it[icon] = action.icon
            it[title] = action.title
            it[description] = action.description
            it[createdAt] = LocalDateTime.now().toString()
            it[modifiedAt] = LocalDateTime.now().toString()
        }.single()[Habits.id]
    }

    override suspend fun updateById(id: Int, action: CreateHabitAction): Int = newSuspendedTransaction {
        Habits.update({Habits.id eq id}) {
            it[icon] = action.icon
            it[title] = action.title
            it[description] = action.description
            it[modifiedAt] = LocalDateTime.now().toString()
        }
    }

    override suspend fun deleteById(id: Int): Int = newSuspendedTransaction {
        Habits.deleteWhere { Habits.id eq id }
    }
}