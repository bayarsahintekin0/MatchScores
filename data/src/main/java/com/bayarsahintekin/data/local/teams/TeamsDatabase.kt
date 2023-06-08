package com.bayarsahintekin.data.local.teams

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bayarsahintekin.data.entity.ListResponseDbData
import com.bayarsahintekin.data.entity.MetaDbData
import com.bayarsahintekin.data.entity.TeamsDbData
import com.bayarsahintekin.data.entity.TeamsKeyDbData
import com.bayarsahintekin.data.mapper.MetaTypeConverter
import com.bayarsahintekin.data.mapper.TeamsConverters

@Database(
    entities = [TeamsDbData::class, TeamsKeyDbData::class,ListResponseDbData::class,MetaDbData::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(MetaTypeConverter::class,TeamsConverters::class)
abstract class TeamsDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun teamRemoteKeysDao(): TeamsKeyDao

}