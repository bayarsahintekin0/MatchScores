package com.bayarsahintekin.data.entity.teams

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bayarsahintekin.data.entity.MetaDbData
import com.bayarsahintekin.data.entity.toData
import com.bayarsahintekin.data.mapper.MetaTypeConverter
import com.bayarsahintekin.data.mapper.TeamsConverters
import com.bayarsahintekin.domain.entity.teams.TeamListEntity

@Entity(tableName = "list_response")
data class ListResponseDbData(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @TypeConverters(TeamsConverters::class)
    @ColumnInfo(name = "teams")
    val data :List<TeamsDbData>,
    @ColumnInfo(name = "meta")
    @TypeConverters(MetaTypeConverter::class)
    val meta: MetaDbData
)

fun ListResponseDbData.toDomain() = TeamListEntity(
    data = data.map { it.toDomain() },
    meta = meta.toData()
)