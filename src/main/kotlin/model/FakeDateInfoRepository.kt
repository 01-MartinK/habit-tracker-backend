package com.habit.model

import com.habit.domain.DateInfo
import java.util.*

class FakeDateInfoRepository : DateInfoRepository {
    private val dateInfos = mutableListOf<DateInfo>()

    override fun findAll(): List<DateInfo> = dateInfos

    override fun findByDate(date: String): DateInfo? = dateInfos.find { it.id == UUID.nameUUIDFromBytes(date.encodeToByteArray()) }

    override fun create(date: String): DateInfo {
        val dateInfo = DateInfo(
            id = UUID.nameUUIDFromBytes(date.encodeToByteArray()),
            completedHabits = emptyList(),
            steps = 0,
        )

        dateInfos.add(dateInfo)

        return dateInfo
    }

    override fun update(date: String, newHabits: List<String>): DateInfo? {
        val dateInfo = dateInfos.find { it.id == UUID.nameUUIDFromBytes(date.encodeToByteArray()) }

        if (dateInfo != null) {
            dateInfo.completedHabits = newHabits
        }

        return dateInfo
    }

    override fun delete(date: String): Boolean = dateInfos.removeIf { it.id == UUID.nameUUIDFromBytes(date.encodeToByteArray()) }
}