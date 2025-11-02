package com.habit.routing

import com.habit.domain.UpdateStreakAction
import com.habit.model.FakeStreakRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.streakRoute(
    fakeStreakRepository: FakeStreakRepository
) {
    get {
        call.respond(
            message = fakeStreakRepository.findAll()
        )
    }

    get("/{id}") {
        val id: String = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)

        val streak = fakeStreakRepository.findByDate(id) ?: call.respond(HttpStatusCode.BadRequest)

        call.respond(
            message = streak
        )
    }

    post("/{id}") {
        val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)

        call.respond(
            message = fakeStreakRepository.create(id)
        )
    }

    put("/{id}") {
        val id: String = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
        val action = call.receive<UpdateStreakAction>()

        val streak = fakeStreakRepository.update(id, action.habits) ?: return@put call.respond(HttpStatusCode.BadRequest)

        call.respond(
            message = streak
        )
    }

    delete("/{id}") {
        val id: String = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        call.respond(
            message = fakeStreakRepository.delete(id)
        )
    }
}