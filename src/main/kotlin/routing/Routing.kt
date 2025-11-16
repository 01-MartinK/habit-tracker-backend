package com.habit.routing

import com.habit.model.FakeDateInfoRepository
import com.habit.model.FakeHabitRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*

fun Application.configureRouting(fakeHabitRepository: FakeHabitRepository, fakeDateInfoRepository: FakeDateInfoRepository) {
    routing {
        route("/api") {
            route("/habits") {
                habitRoute(fakeHabitRepository)
            }

            route("/date-info") {
                dateInfoRoute(fakeDateInfoRepository)
            }

            get("/healthcheck") {
                call.respond(
                    message = HttpStatusCode.OK, typeInfo = typeInfo<String>()
                )
            }
        }
    }
}
