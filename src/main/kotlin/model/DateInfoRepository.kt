package com.habit.model

import com.habit.domain.DateInfo
import java.util.*

interface DateInfoRepository {
    suspend fun findAll(): List<DateInfo>
    suspend fun findByDate(date: String): DateInfo?
    suspend fun create(date: String): UUID
    suspend fun update(date: String, newHabits: List<String>): Int
    suspend fun delete(date: String): Int
}