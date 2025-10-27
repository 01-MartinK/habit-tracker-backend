package com.habit.model

import com.habit.domain.CreateHabitAction
import com.habit.domain.Habit
import java.time.LocalDateTime
import java.util.*

class FakeHabitRepository : HabitRepository {
    private val habits = mutableListOf<Habit>(
        Habit(UUID.randomUUID(),
            "person-running",
            "Workout",
            "6:00 - 2h",
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString()
        ),
        Habit(UUID.randomUUID(),
            "moon",
            "Sleep",
            "22:00 - 6:00",
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString()
        ),
        Habit(UUID.randomUUID(),
            "person-praying",
            "Yoga",
            "16:00 - 1h",
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString()
        ),
        Habit(UUID.randomUUID(),
            "person-running",
            "Mouth exercises",
            "8:00 - 10min",
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString()
        ),
    )

    override fun findAll(): List<Habit> = habits
    override fun findById(id: String): Habit? = habits.find { it.id == UUID.fromString(id) }
    override fun create(action: CreateHabitAction): Habit {
        val habit = Habit(action.id ?: UUID.randomUUID(),
            action.icon,
            action.title,
            action.description,
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString()
        )

        habits.add(habit)

        return habit
    }

    override fun updateById(id: String, action: CreateHabitAction): Habit? {
        val oldHabit: Habit? = habits.find { it.id == UUID.fromString(id) }

        if (oldHabit != null) {
            val habit = Habit(
                id = oldHabit.id,
                icon = action.icon,
                title = action.title,
                description = action.description,
                createdAt = oldHabit.createdAt,
                modifiedAt = LocalDateTime.now().toString(),
            )

            habits.find { it.id == UUID.fromString(id) } == habit

            return habit
        }

        return null
    }

    override fun deleteById(id: String): Boolean {
        return habits.removeIf { it.id == UUID.fromString(id) }
    }
}