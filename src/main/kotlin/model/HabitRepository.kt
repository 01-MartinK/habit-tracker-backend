package com.habit.model

import com.habit.domain.CreateHabitAction
import com.habit.domain.Habit

interface HabitRepository {
    suspend fun findAll(): List<Habit>
    suspend fun findById(id: Int): Habit?
    suspend fun create(action: CreateHabitAction): Int
    suspend fun updateById(id: Int, action: CreateHabitAction): Int?
    suspend fun deleteById(id: Int): Int
}