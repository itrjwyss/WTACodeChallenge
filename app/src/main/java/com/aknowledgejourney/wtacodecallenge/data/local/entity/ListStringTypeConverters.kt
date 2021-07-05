package com.aknowledgejourney.wtacodecallenge.data.local.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListStringTypeConverters {

    private val gson = Gson()

    @TypeConverter
    fun parseListToString(values: List<String>): String {
        return gson.toJson(values)
    }

    @TypeConverter
    fun parseStringToList(data: String?): List<String> {
        if (data == null) {
            return ArrayList()
        }

        val listType: Type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, listType)
    }

}