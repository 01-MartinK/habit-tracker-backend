package org.mk.hbtracker.engine

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.mk.hbtracker.entities.Habit;

@RestController
public class HabitController {
    @GetMapping("/habits/")
    fun listHabits(model: Model): List<Habit> {
        return
    }

    @GetMapping("/habits/")
    fun createHabit(@RequestParam(value = "name", defaultValue = "world") name: String): Habit {
        return new
    }
}