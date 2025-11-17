package com.habit.routing

import com.habit.domain.UpdateDateInfoAction
import com.habit.services.DateInfoService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.dateInfoRoute(
    dateInfoService: DateInfoService
) {
    get {
        call.respond(
            message = dateInfoService.findAll()
        )
    }

    get("/{id}") {
        val id: String = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)

        val dateInfo = dateInfoService.findByDate(id) ?: call.respond(HttpStatusCode.NotFound)

        call.respond(
            message = dateInfo
        )
    }

    post("/{id}") {
        val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)

        call.respond(
            message = dateInfoService.create(id)
        )
    }

    put("/{id}") {
        val id: String = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
        val action = call.receive<UpdateDateInfoAction>()

        val dateInfo = dateInfoService.update(id, action.habits)

        call.respond(
            message = dateInfo
        )
    }

    delete("/{id}") {
        val id: String = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
        call.respond(
            message = dateInfoService.delete(id)
        )
    }
}