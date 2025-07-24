package org.mk.hbtracker.services

import org.mk.hbtracker.HabitRepository
import org.mk.hbtracker.domain.Habit
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class HabitService(val repository: HabitRepository) {
    fun getAllHabits(): List<Habit> = repository.findAll()

    fun getAllHabitsForUser(userId: Long) = repository.findAll()

    fun createHabit(habit: Habit) = repository.save<Habit>(habit)

    fun updateHabit(id: Long, habit: Habit): Habit {
        return if (repository.existsById(id)) {
            repository.save<Habit>(
                Habit(
                    id = id,
                    name = habit.name,
                    startTime = habit.startTime,
                    duration = habit.duration,
                    createdAt = habit.createdAt,
                    modifiedAt = LocalDateTime.now().toString(),
                    userId = habit.userId,
                )
            )
        } else throw ChangeSetPersister.NotFoundException()
    }

    fun deleteHabit(id: Long) = repository.deleteById(id)
}