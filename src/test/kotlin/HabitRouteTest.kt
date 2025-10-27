package com.habit

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.habit.domain.CreateHabitAction
import com.habit.domain.Habit
import com.habit.model.FakeHabitRepository
import com.habit.routing.configureRouting
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import java.util.*
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
    private val objectMapper = ObjectMapper().registerKotlinModule()
    private val mockAction = CreateHabitAction(id = UUID.randomUUID(), icon = "person-floor", title = "Floor stretch", description = "Stretch on the floor")


    @Test
    fun `get all habits`() = testApplication {
        application {
            configureSerialization()
            configureHTTP()
            configureRouting(FakeHabitRepository())
        }

        client.get(url).apply {
            val response: String = call.response.body<String>()
            try {
                val habits: List<*> = objectMapper.readValue(response, List::class.java)

                assertEquals(HttpStatusCode.OK, call.response.status)
                assertEquals(false, habits.isEmpty())
            } catch (e: JsonProcessingException) {
                println("Failed to parse JSON: ${e.message}")
            }
        }
    }

    @Test
    fun `create new habit`() = testApplication {
        application {
            configureSerialization()
            configureHTTP()
            configureRouting(FakeHabitRepository())
        }

        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(
                objectMapper.writeValueAsString(
                    mockAction
                )
            )
        }

        val habit: Habit = objectMapper.readValue(response.body<String>(), Habit::class.java)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(mockAction.icon, habit.icon)
        assertEquals(mockAction.title, habit.title)
        assertEquals(mockAction.description, habit.description)
    }

    @Test
    fun `update habit`() = testApplication {
        application {
            configureSerialization()
            configureHTTP()
            configureRouting(FakeHabitRepository())
        }

        var response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(
                objectMapper.writeValueAsString(
                    mockAction
                )
            )
        }

        var habit: Habit = objectMapper.readValue(response.body<String>(), Habit::class.java)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(mockAction.id, habit.id)

        response = client.put(url+"/"+mockAction.id) {
            contentType(ContentType.Application.Json)
            setBody(
                objectMapper.writeValueAsString(
                    mockAction.copy(title = "Standing stretch")
                )
            )
        }

        habit = objectMapper.readValue(response.body<String>(), Habit::class.java)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Standing stretch", habit.title)
    }

    @Test
    fun `delete habit by id`() = testApplication {
        application {
            configureSerialization()
            configureHTTP()
            configureRouting(FakeHabitRepository())
        }

        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(
                objectMapper.writeValueAsString(
                    mockAction
                )
            )
        }

        val habit: Habit = objectMapper.readValue(response.body<String>(), Habit::class.java)

        assertEquals(HttpStatusCode.OK, response.status)

        val deleteResponse = client.delete(url + "/" + habit.id)

        assertEquals(HttpStatusCode.OK, deleteResponse.status)
        assertEquals(true, deleteResponse.body<String>().toBoolean())
    }
}