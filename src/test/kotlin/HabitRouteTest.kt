package com.habit

import com.habit.model.FakeHabitRepository
import com.habit.routing.configureRouting
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

/*
Tests needed
- Get
- Get ID
- Post
- Put
- Delete
 */

class HabitRouteTest {
    private val url = "/api/habits"

    @Test
    fun `get all habits`() = testApplication {
        application {
            configureSerialization()
            configureHTTP()
            configureRouting(FakeHabitRepository())
        }

        client.get(url).apply {
            val response = call.response.body<String>()

            assertEquals(HttpStatusCode.OK, call.response.status)
            assertEquals(false, response.isEmpty())
        }
    }

}