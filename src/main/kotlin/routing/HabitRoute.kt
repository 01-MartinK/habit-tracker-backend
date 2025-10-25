package com.habit.routing

import com.habit.domain.toCreateAction
import com.habit.model.FakeHabitRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import java.util.UUID

fun Route.habitRoute(
    fakeHabitRepository: FakeHabitRepository
) {
    get {
        val habits = fakeHabitRepository.allHabits()

        call.respond(
            message = habits
        )
    }

    get("/{id}") {
        val id: String = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)

        val habit = fakeHabitRepository.allHabits().find { it.id == UUID.fromString(id) } ?: return@get call.respond(
            HttpStatusCode.NotFound
        )

        call.respond(
            message = habit
        )
    }

    post {
        val params = call.receiveParameters()

        val action = params.toCreateAction() ?: return@post call.respond(HttpStatusCode.BadRequest)

        call.respond(
            message = fakeHabitRepository.createHabit(action)
        )
    }

    put("/{id}") {
        val params = call.receiveParameters()
        val id: String = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)

        if (id.isEmpty()) return@put call.respond(HttpStatusCode.BadRequest)

        val action = params.toCreateAction() ?: return@put call.respond(HttpStatusCode.BadRequest)
        val habit = fakeHabitRepository.updateHabit(id, action) ?: return@put call.respond(HttpStatusCode.BadRequest)

        call.respond(
            message = habit
        )
    }

    delete("/{id}") {
        val id: String = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        call.respond(
            message = fakeHabitRepository.deleteHabit(id)
        )
    }
}