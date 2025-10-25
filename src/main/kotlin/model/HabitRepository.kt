package com.habit.model

import com.habit.domain.CreateHabitAction
import com.habit.domain.Habit

interface HabitRepository {
    fun allHabits(): List<Habit>
    fun getHabitById(id: String): Habit?
    fun createHabit(action: CreateHabitAction): Habit
    fun updateHabit(id: String, action: CreateHabitAction): Habit?
    fun deleteHabit(id: String): Boolean
}