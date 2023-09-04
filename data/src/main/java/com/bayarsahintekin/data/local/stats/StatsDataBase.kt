package com.bayarsahintekin.data.local.stats

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bayarsahintekin.data.entity.stats.StatsDbData
import com.bayarsahintekin.data.entity.stats.StatsRemoteKeysDbData
import com.bayarsahintekin.data.mapper.GameTypeConverter
import com.bayarsahintekin.data.mapper.MetaTypeConverter
import com.bayarsahintekin.data.mapper.PlayerConverters
import com.bayarsahintekin.data.mapper.PlayerTypeConverter
import com.bayarsahintekin.data.mapper.StatsConverters
import com.bayarsahintekin.data.mapper.StatsTypeConverter
import com.bayarsahintekin.data.mapper.TeamTypeConverter
import com.bayarsahintekin.data.mapper.TeamsConverters

@Database(
    entities = [StatsDbData::class, StatsRemoteKeysDbData::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    MetaTypeConverter::class,
    StatsConverters::class,
    StatsTypeConverter::class,
    PlayerConverters::class,
    PlayerTypeConverter::class,
    GameTypeConverter::class,
    GameTypeConverter::class,
    TeamTypeConverter::class,
    TeamsConverters::class
)
abstract class StatsDataBase : RoomDatabase() {
    abstract fun statsDao(): StatsDao
    abstract fun statsKeyDao(): StatsRemoteKeysDao

}