package com.habit.model

import com.habit.domain.CreateHabitAction
import com.habit.domain.Habit
import com.habit.domain.UpdateHabitAction

interface HabitRepository {
    fun allHabits(): List<Habit>
    fun getHabitById(id: Long): Habit?
    fun createHabit(id: Long, action: CreateHabitAction): Habit
    fun updateHabit(id: Long, action: UpdateHabitAction): Habit?
    fun deleteHabit(id: Long): Boolean
}