package com.habit.routing

import com.habit.model.FakeHabitRepository
import com.habit.model.FakeStreakRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*

fun Application.configureRouting(fakeHabitRepository: FakeHabitRepository, fakeStreakRepository: FakeStreakRepository) {
    routing {
        route("/api") {
            route("/habits") {
                habitRoute(fakeHabitRepository)
            }

            route("/streaks") {
                streakRoute(fakeStreakRepository)
            }

            get("/healthcheck") {
                call.respond(
                    message = HttpStatusCode.OK, typeInfo = typeInfo<String>()
                )
            }
        }
    }
}
