package com.bayarsahintekin.data.mapper

import androidx.room.TypeConverter
import com.bayarsahintekin.data.entity.MetaDbData
import com.bayarsahintekin.data.entity.games.GameDbData
import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.teams.TeamsDbData
import com.bayarsahintekin.domain.entity.GameEntity
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.TeamEntity
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
    fun fromTeamListToString(date: List<TeamsDbData>): String? {
        return gson.toJson(date)
    }
}

class PlayerConverters {
    var gson = Gson()
    @TypeConverter
    fun fromStringToPlayerList(value: String?): List<PlayersDbData>? {
        val listType: Type = object : TypeToken<List<PlayersDbData?>?>() {}.type
        return gson.fromJson<List<PlayersDbData>>(value, listType)
    }

    @TypeConverter
    fun fromPlayerListToString(date: List<PlayersDbData>): String? {
        return gson.toJson(date)
    }
}

class GameConverters {
    var gson = Gson()
    @TypeConverter
    fun fromStringToGameList(value: String?): List<GameDbData>? {
        val listType: Type = object : TypeToken<List<GameDbData?>?>() {}.type
        return gson.fromJson<List<GameDbData>>(value, listType)
    }

    @TypeConverter
    fun fromGameListToString(date: List<GameDbData>): String? {
        return gson.toJson(date)
    }
}

class MetaTypeConverter {

    @TypeConverter
    fun appToString(app: MetaDbData): String = Gson().toJson(app)

    @TypeConverter
    fun stringToApp(string: String): MetaDbData = Gson().fromJson(string, MetaDbData::class.java)

}

class TeamTypeConverter {

    @TypeConverter
    fun appToString(app: TeamEntity): String = Gson().toJson(app)

    @TypeConverter
    fun stringToApp(string: String): TeamEntity = Gson().fromJson(string, TeamEntity::class.java)

}

class PlayerTypeConverter {
    @TypeConverter
    fun playerEntityToString(app: PlayerEntity): String = Gson().toJson(app)

    @TypeConverter
    fun stringToPlayerEntity(string: String): PlayerEntity = Gson().fromJson(string, PlayerEntity::class.java)

}

class GameTypeConverter {
    @TypeConverter
    fun gameEntityToString(app: GameEntity): String = Gson().toJson(app)

    @TypeConverter
    fun stringToGameEntity(string: String): GameEntity = Gson().fromJson(string, GameEntity::class.java)

}