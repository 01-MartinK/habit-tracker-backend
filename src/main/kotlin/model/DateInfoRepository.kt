package com.habit.model

import com.habit.domain.DateInfo

interface DateInfoRepository {
    fun findAll(): List<DateInfo>
    fun findByDate(date: String): DateInfo?
    fun create(date: String): DateInfo
    fun update(date: String, newHabits: List<String>): DateInfo?
    fun delete(date: String): Boolean
}