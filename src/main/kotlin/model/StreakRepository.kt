package com.habit.model

import com.habit.domain.Streak

interface StreakRepository {
    fun findAll(): List<Streak>
    fun findByDate(date: String): Streak?
    fun create(date: String): Streak
    fun update(date: String, newHabits: List<String>): Streak?
    fun delete(date: String): Boolean
}