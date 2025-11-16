package com.habit

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.habit.domain.DateInfo
import com.habit.domain.Habit
import com.habit.domain.UpdateDateInfoAction
import com.habit.model.FakeDateInfoRepository
import com.habit.model.FakeHabitRepository
import com.habit.routing.configureRouting
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import java.time.LocalDate
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals


class DateInfoRouteTest {
    private val url = "/api/date-info"
    private val objectMapper = ObjectMapper().registerKotlinModule()
    private var configApplication: ApplicationTestBuilder.() -> Unit = {
        application {
            configureSerialization()
            configureHTTP()
            configureRouting(FakeHabitRepository(), FakeDateInfoRepository())
        }
    }

    fun toHabitsList(hashMap: List<*>): List<Habit> {
        return hashMap.map { objectMapper.convertValue(it, Habit::class.java) }
    }

    @Test
    fun `create new DateInfo by dateInfo`() = testApplication {
        configApplication()

        val date = LocalDate.now().toString()

        val response = client.post("$url/$date")
        val dateInfo: DateInfo = objectMapper.readValue(response.body<String>(), DateInfo::class.java)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(UUID.nameUUIDFromBytes(date.encodeToByteArray()), dateInfo.id)
        assertEquals(0, dateInfo.completedHabits.size)
        assertEquals(0, dateInfo.steps)
    }

    @Test
    fun `add completed habits to dateInfo`() = testApplication {
        configApplication()

        val date = LocalDate.now().toString()
        client.post("$url/$date")

        val habitsResponse = client.get("/api/habits").body<String>()
        val habitsBroken: List<*> = objectMapper.readValue(habitsResponse, List::class.java)
        val habits = toHabitsList(habitsBroken)

        val response = client.put("$url/$date") {
            contentType(ContentType.Application.Json)
            setBody(
                objectMapper.writeValueAsString(
                    UpdateDateInfoAction(
                        listOf(
                            habits[0].id.toString(),
                            habits[1].id.toString()
                        )
                    )
                )
            )
        }

        val dateInfo: DateInfo = objectMapper.readValue(response.body<String>(), DateInfo::class.java)

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(UUID.nameUUIDFromBytes(date.encodeToByteArray()), dateInfo.id)
        assertEquals(2, dateInfo.completedHabits.size)
        assertEquals(0, dateInfo.steps)
    }

    @Test
    fun `delete dateInfo`() = testApplication {
        configApplication()

        val date = LocalDate.now().toString()
        val createResponse = client.post("$url/$date")

        assertEquals(HttpStatusCode.OK, createResponse.status)

        val response = client.delete("$url/$date")

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(true, response.body<String>().toBoolean())
    }
}