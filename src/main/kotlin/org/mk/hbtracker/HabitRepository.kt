package org.mk.hbtracker

import org.mk.hbtracker.domain.Habit
import org.springframework.data.jpa.repository.JpaRepository

interface HabitRepository : JpaRepository<Habit, Long> {}