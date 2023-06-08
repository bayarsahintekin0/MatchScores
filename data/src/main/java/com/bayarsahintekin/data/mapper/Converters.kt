package com.bayarsahintekin.data.mapper

import androidx.room.TypeConverter
import com.bayarsahintekin.data.entity.MetaDbData
import com.bayarsahintekin.data.entity.TeamsDbData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TeamsConverters {
    var gson = Gson()
    @TypeConverter
    fun fromStringToTeamList(value: String?): List<TeamsDbData>? {
        val listType: Type = object : TypeToken<List<TeamsDbData?>?>() {}.type
        return gson.fromJson<List<TeamsDbData>>(value, listType)
    }

    @TypeConverter
    fun dateToTimestamp(date: List<TeamsDbData>): String? {
        return gson.toJson(date)
    }
}

class MetaTypeConverter {

    @TypeConverter
    fun appToString(app: MetaDbData): String = Gson().toJson(app)

    @TypeConverter
    fun stringToApp(string: String): MetaDbData = Gson().fromJson(string, MetaDbData::class.java)

}