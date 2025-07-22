package org.mk.hbtracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HabitTrackerBackendApplication

fun main(args: Array<String>) {
	runApplication<HabitTrackerBackendApplication>(*args)
}
