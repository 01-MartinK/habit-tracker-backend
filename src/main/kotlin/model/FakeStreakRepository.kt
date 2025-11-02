package com.habit.model

import com.habit.domain.Streak
import java.util.*

class FakeStreakRepository : StreakRepository {
    private val streaks = mutableListOf<Streak>()

    override fun findAll(): List<Streak> = streaks

    override fun findByDate(date: String): Streak? = streaks.find { it.id == UUID.fromString(date) }

    override fun create(date: String): Streak {
        val streak = Streak(
            id = UUID.fromString(date),
            completedHabits = emptyList()
        )

        streaks.add(streak)

        return streak
    }

    override fun update(date: String, newHabits: List<String>): Streak? {
        val streak = streaks.find { it.id == UUID.fromString(date) }

        if (streak != null) {
            streak.completedHabits = newHabits
        }

        return streak
    }

    override fun delete(date: String): Boolean = streaks.removeIf { it.id == UUID.fromString(date) }
}