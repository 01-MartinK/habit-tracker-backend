package org.mk.hbtracker.engine

import org.mk.hbtracker.domain.CreateHabitRequest
import org.springframework.web.bind.annotation.RestController
import org.mk.hbtracker.domain.Habit;
import org.mk.hbtracker.domain.UpdateHabitRequest
import org.mk.hbtracker.services.HabitService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/habits")
public class HabitController(private val service: HabitService) {
    @GetMapping("/")
    fun getHabits(): List<Habit> = service.getAllHabits()

    @PostMapping("/")
    fun createHabit(@RequestBody body: CreateHabitRequest): Habit = service.createHabit(Habit(
        name = body.name,
        startTime = body.startTime,
        duration = body.duration,
        createdAt = LocalDateTime.now(),
        modifiedAt = LocalDateTime.now()
    ))

    @PutMapping("/")
    fun updateHabit(@RequestBody body: UpdateHabitRequest): Habit = service.updateHabit(body.id, Habit(
        id = body.id,
        name = body.name,
        startTime = body.startTime,
        duration = body.duration,
        createdAt = LocalDateTime.now(),
        modifiedAt = LocalDateTime.now()
    ))

    @DeleteMapping("/")
    fun deleteHabit(id: Long) = service.deleteHabit(id)
}