package com.habit.routing

import com.habit.model.FakeHabitRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Route.habitRoute(
    fakeHabitRepository: FakeHabitRepository
) {
    get {

    }

    get("/{id}") {

    }

    post {

    }

    put {

    }

    delete {

    }
}