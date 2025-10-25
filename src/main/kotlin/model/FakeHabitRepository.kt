package com.habit.model

import com.habit.domain.CreateHabitAction
import com.habit.domain.Habit
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.UUID

class FakeHabitRepository : HabitRepository {
    private val habits = mutableListOf<Habit>(
        Habit(UUID.randomUUID(),
            "person-running",
            "Workout",
            "6:00 - 2h",
            SimpleDateFormat(LocalDate.now().toString()),
            SimpleDateFormat(LocalDate.now().toString())
        ),
        Habit(UUID.randomUUID(),
            "moon",
            "Sleep",
            "22:00 - 6:00",
            SimpleDateFormat(LocalDate.now().toString()),
            SimpleDateFormat(LocalDate.now().toString())
        ),
        Habit(UUID.randomUUID(),
            "person-praying",
            "Yoga",
            "16:00 - 1h",
            SimpleDateFormat(LocalDate.now().toString()),
            SimpleDateFormat(LocalDate.now().toString())
        ),
        Habit(UUID.randomUUID(),
            "person-running",
            "Mouth exercises",
            "8:00 - 10min",
            SimpleDateFormat(LocalDate.now().toString()),
            SimpleDateFormat(LocalDate.now().toString())
        ),
    )

    override fun allHabits(): List<Habit> = habits
    override fun getHabitById(id: String): Habit? = habits.find { it.id == UUID.fromString(id) }
    override fun createHabit(action: CreateHabitAction): Habit {
        val habit = Habit(UUID.randomUUID(),
            action.icon,
            action.title,
            action.description,
            SimpleDateFormat(LocalDate.now().toString()),
            SimpleDateFormat(LocalDate.now().toString())
        )

        habits.add(habit)

        return habit
    }

    override fun updateHabit(id: String, action: CreateHabitAction): Habit? {
        val oldHabit: Habit? = habits.find { it.id == UUID.fromString(id) }

        if (oldHabit != null) {
            val habit = Habit(
                id = oldHabit.id,
                icon = action.icon,
                title = action.title,
                description = action.description,
                createdAt = oldHabit.createdAt,
                modifiedAt = SimpleDateFormat(LocalDate.now().toString()),
            )

            habits.find { it.id == UUID.fromString(id) } == habit

            return habit
        }

        return null
    }

    override fun deleteHabit(id: String): Boolean {
        return habits.removeIf { it.id == UUID.fromString(id) }
    }
}