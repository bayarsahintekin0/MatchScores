package com.bayarsahintekin.data.mapper

import androidx.room.TypeConverter
import com.bayarsahintekin.data.entity.MetaDbData
import com.bayarsahintekin.data.entity.games.GameDbData
import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.stats.StatsDbData
import com.bayarsahintekin.data.entity.teams.TeamsDbData
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.stats.StatsGameEntity
import com.bayarsahintekin.domain.entity.stats.StatsPlayerEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
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

class StatsConverters {
    var gson = Gson()
    @TypeConverter
    fun fromStringToStatList(value: String?): List<StatsDbData>? {
        val listType: Type = object : TypeToken<List<StatsDbData?>?>() {}.type
        return gson.fromJson<List<StatsDbData>>(value, listType)
    }

    @TypeConverter
    fun fromStatListToString(date: List<StatsDbData>): String? {
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
    fun appToString(app: TeamsDbData): String = Gson().toJson(app)

    @TypeConverter
    fun stringToApp(string: String): TeamsDbData = Gson().fromJson(string, TeamsDbData::class.java)

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

class StatsTypeConverter {

    @TypeConverter
    fun statsPlayerEntityToString(app: StatsPlayerEntity): String = Gson().toJson(app)

    @TypeConverter
    fun stringToStatsPlayerEntity(string: String): StatsPlayerEntity = Gson().fromJson(string, StatsPlayerEntity::class.java)

    @TypeConverter
    fun statsGameEntityToString(app: StatsGameEntity): String = Gson().toJson(app)

    @TypeConverter
    fun stringToStatsGameEntity(string: String): StatsGameEntity = Gson().fromJson(string, StatsGameEntity::class.java)

    @TypeConverter
    fun statsEntityToString(app: StatsEntity): String = Gson().toJson(app)

    @TypeConverter
    fun stringToStatEntity(string: String): StatsEntity = Gson().fromJson(string, StatsEntity::class.java)

}