package org.mk.hbtracker.engine

import org.mk.hbtracker.domain.CreateHabitRequest
import org.mk.hbtracker.domain.Habit
import org.mk.hbtracker.domain.UpdateHabitRequest
import org.mk.hbtracker.services.HabitService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/habits")
public class HabitController(private val service: HabitService) {
    @GetMapping("/")
    fun getHabits(): List<Habit> = service.getAllHabits()

    @PostMapping("/")
    fun createHabit(@RequestBody body: CreateHabitRequest): Habit = service.createHabit(
        Habit(
            name = body.name,
            startTime = body.startTime,
            duration = body.duration,
            createdAt = LocalDateTime.now().toString(),
            modifiedAt = LocalDateTime.now().toString(),
        )
    )

    @PutMapping("/{id}")
    fun updateHabit(@PathVariable id: Long, @RequestBody body: UpdateHabitRequest): Habit = service.updateHabit(
        id, Habit(
            id = id,
            name = body.name,
            startTime = body.startTime,
            duration = body.duration,
            createdAt = LocalDateTime.now().toString(),
            modifiedAt = LocalDateTime.now().toString()
        )
    )

    @DeleteMapping("/{id}")
    fun deleteHabit(@PathVariable id: Long) = service.deleteHabit(id)
}