package com.aknowledgejourney.wtacodecallenge.data.local.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListIntTypeConverters {

    private val gson = Gson()

    @TypeConverter
    fun parseListToString(values: List<Int>): String {
        return gson.toJson(values)
    }

    @TypeConverter
    fun parseStringToList(data: String?): List<Int> {
        if (data == null) {
            return ArrayList()
        }

        val listType: Type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(data, listType)
    }

}