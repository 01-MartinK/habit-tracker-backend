package com.habit.model

import com.habit.domain.CreateHabitAction
import com.habit.domain.Habit

interface HabitRepository {
    fun findAll(): List<Habit>
    fun findById(id: String): Habit?
    fun create(action: CreateHabitAction): Habit
    fun updateById(id: String, action: CreateHabitAction): Habit?
    fun deleteById(id: String): Boolean
}