package com.habit

import com.habit.model.FakeHabitRepository
import com.habit.routing.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val fakeHabitRepository = FakeHabitRepository()

    configureSerialization()
    configureHTTP()
    configureRouting(fakeHabitRepository)
}
