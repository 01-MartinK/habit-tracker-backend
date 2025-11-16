package com.habit.routing

import com.habit.domain.UpdateDateInfoAction
import com.habit.model.FakeDateInfoRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.dateInfoRoute(
    fakeDateInfoRepository: FakeDateInfoRepository
) {
    get {
        call.respond(
            message = fakeDateInfoRepository.findAll()
        )
    }

    get("/{id}") {
        val id: String = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)

        val dateInfo = fakeDateInfoRepository.findByDate(id) ?: call.respond(HttpStatusCode.BadRequest)

        call.respond(
            message = dateInfo
        )
    }

    post("/{id}") {
        val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)

        call.respond(
            message = fakeDateInfoRepository.create(id)
        )
    }

    put("/{id}") {
        val id: String = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
        val action = call.receive<UpdateDateInfoAction>()

        val dateInfo = fakeDateInfoRepository.update(id, action.habits) ?: return@put call.respond(HttpStatusCode.BadRequest)

        call.respond(
            message = dateInfo
        )
    }

    delete("/{id}") {
        val id: String = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        call.respond(
            message = fakeDateInfoRepository.delete(id)
        )
    }
}