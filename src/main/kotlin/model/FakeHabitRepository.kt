package com.habit.model

import com.habit.domain.CreateHabitAction
import com.habit.domain.Habit
import com.habit.domain.UpdateHabitAction
import com.habit.model.HabitRepository
import java.text.SimpleDateFormat
import java.time.LocalDate

class FakeHabitRepository : HabitRepository {
    private val habits = mutableListOf<Habit>(
        Habit(0,
            "person-running",
            "Workout",
            "6:00 - 2h",
            SimpleDateFormat(LocalDate.now().toString()),
            SimpleDateFormat(LocalDate.now().toString())
        ),
        Habit(1,
            "moon",
            "Sleep",
            "22:00 - 6:00",
            SimpleDateFormat(LocalDate.now().toString()),
            SimpleDateFormat(LocalDate.now().toString())
        ),
        Habit(2,
            "person-praying",
            "Yoga",
            "16:00 - 1h",
            SimpleDateFormat(LocalDate.now().toString()),
            SimpleDateFormat(LocalDate.now().toString())
        ),
        Habit(3,
            "person-running",
            "Mouth exercises",
            "8:00 - 10min",
            SimpleDateFormat(LocalDate.now().toString()),
            SimpleDateFormat(LocalDate.now().toString())
        ),
    )

    override fun allHabits(): List<Habit> = habits
    override fun getHabitById(id: Long): Habit? = habits.find { it.id == id }
    override fun createHabit(id: Long, action: CreateHabitAction): Habit {
        val habit: Habit = Habit(0,
            action.icon,
            action.title,
            action.description,
            SimpleDateFormat(LocalDate.now().toString()),
            SimpleDateFormat(LocalDate.now().toString())
        )

        habits.add(habit);

        return habit
    }

    override fun updateHabit(id: Long, action: UpdateHabitAction): Habit? {
        val habit: Habit? = habits.find { it.id == id }

        return habit;
    }

    override fun deleteHabit(id: Long): Boolean {
        TODO("Not yet implemented")
    }
}