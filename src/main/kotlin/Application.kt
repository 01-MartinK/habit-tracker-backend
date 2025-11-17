package com.habit

import com.habit.routing.configureRouting
import com.habit.services.DateInfoService
import com.habit.services.HabitService
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseFactory.init()

    configureSerialization()
    configureHTTP()
    configureRouting(HabitService(), DateInfoService())
}
