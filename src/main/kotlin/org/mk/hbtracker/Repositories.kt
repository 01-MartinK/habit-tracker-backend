package org.mk.hbtracker

import org.mk.hbtracker.domain.Habit
import org.mk.hbtracker.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface HabitRepository : JpaRepository<Habit, Long> {}

interface UserRepository : JpaRepository<User, Long> {}