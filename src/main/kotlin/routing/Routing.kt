package com.habit.routing

import com.habit.model.FakeHabitRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.typeInfo

fun Application.configureRouting(fakeHabitRepository: FakeHabitRepository) {
    routing {
        route("/api") {
            route("/habits") {
                habitRoute(fakeHabitRepository)
            }

            get("/healthcheck") {
                call.respond(
                    message = HttpStatusCode.OK,
                    typeInfo = typeInfo<String>()
                )
            }
        }
    }
}
