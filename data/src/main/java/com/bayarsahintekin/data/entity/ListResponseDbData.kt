package com.bayarsahintekin.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bayarsahintekin.data.mapper.MetaTypeConverter
import com.bayarsahintekin.data.mapper.TeamsConverters
import com.bayarsahintekin.domain.entity.ListResponseEntity

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

fun ListResponseDbData.toDomain() = ListResponseEntity(
    data = data.map { it.toData() },
    meta = meta.toData()
)