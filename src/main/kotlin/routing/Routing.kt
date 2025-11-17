package com.habit.routing

import com.habit.services.DateInfoService
import com.habit.services.HabitService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.util.reflect.*

fun Application.configureRouting(habitService: HabitService, dateInfoService: DateInfoService) {
    routing {
        route("/api") {
            route("/habits") {
                habitRoute(habitService)
            }

            route("/date-info") {
                dateInfoRoute(dateInfoService)
            }

            get("/healthcheck") {
                call.respond(
                    message = HttpStatusCode.OK, typeInfo = typeInfo<String>()
                )
            }
        }
    }
}
