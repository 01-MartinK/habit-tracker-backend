package com.habit.routing

import com.habit.domain.CreateHabitAction
import com.habit.services.HabitService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.habitRoute(
    habitService: HabitService
) {
    get {
        call.respond(
            message = habitService.findAll()
        )
    }

    get("/{id}") {
        val id: Int = call.parameters["id"]?.toInt() ?: return@get call.respond(HttpStatusCode.BadRequest)

        val habit = habitService.findById(id) ?: return@get call.respond(
            HttpStatusCode.NotFound
        )

        call.respond(
            message = habit
        )
    }

    post {
        val action = call.receive<CreateHabitAction>()

        call.respond(
            message = habitService.create(action)
        )
    }

    put("/{id}") {
        val action = call.receive<CreateHabitAction>()
        val id: Int = call.parameters["id"]?.toInt() ?: return@put call.respond(HttpStatusCode.BadRequest)

        val habit = habitService.updateById(id, action)

        call.respond(
            message = habit
        )
    }

    delete("/{id}") {
        val id: Int = call.parameters["id"]?.toInt() ?: return@delete call.respond(HttpStatusCode.BadRequest)
        call.respond(
            message = habitService.deleteById(id)
        )
    }
}