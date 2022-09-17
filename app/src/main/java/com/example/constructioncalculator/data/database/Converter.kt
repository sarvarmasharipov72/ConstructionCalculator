package com.example.constructioncalculator.data.database


import androidx.room.TypeConverter
import com.example.constructioncalculator.model.Jobs
import com.example.constructioncalculator.model.Material
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromString(list: List<Material>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromList(json: String): List<Material> {
        return json.fromJson<List<Material>>()!!
    }
    @TypeConverter
    fun fromStrings(list: List<Jobs>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromLists(json: String): List<Jobs> {
        return json.fromJson<List<Jobs>>()!!
    }

    private inline fun <reified T : Any> String?.fromJson(): T? = this?.let {
        val type = object : TypeToken<T>() {}.type
        Gson().fromJson(this, type)
    }
}