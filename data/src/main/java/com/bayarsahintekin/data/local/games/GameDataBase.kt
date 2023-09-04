package com.bayarsahintekin.data.local.games

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bayarsahintekin.data.entity.games.GameDbData
import com.bayarsahintekin.data.entity.games.GamesRemoteKeysDbData
import com.bayarsahintekin.data.mapper.GameConverters
import com.bayarsahintekin.data.mapper.GameTypeConverter
import com.bayarsahintekin.data.mapper.MetaTypeConverter
import com.bayarsahintekin.data.mapper.TeamTypeConverter
import com.bayarsahintekin.data.mapper.TeamsConverters

@Database(
    entities = [GameDbData::class, GamesRemoteKeysDbData::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    MetaTypeConverter::class,
    GameConverters::class,
    GameTypeConverter::class,
    TeamTypeConverter::class,
    TeamsConverters::class
)
abstract class GameDataBase : RoomDatabase() {
    abstract fun gamesDao(): GameDao
    abstract fun gameKeysDao(): GameRemoteKeysDao

}