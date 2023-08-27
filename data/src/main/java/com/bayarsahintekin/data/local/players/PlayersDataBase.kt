package com.bayarsahintekin.data.local.players

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.bayarsahintekin.data.entity.players.PlayersDbData
import com.bayarsahintekin.data.entity.players.PlayersRemoteKeysDbData
import com.bayarsahintekin.data.mapper.MetaTypeConverter
import com.bayarsahintekin.data.mapper.TeamTypeConverter
import com.bayarsahintekin.data.mapper.TeamsConverters

@Database(
    entities = [PlayersDbData::class, PlayersRemoteKeysDbData::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(MetaTypeConverter::class, TeamsConverters::class,TeamTypeConverter::class)
abstract class PlayersDataBase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun playerKeyDao(): PlayersKeyDao

}