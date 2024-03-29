package com.bayarsahintekin.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayarsahintekin.domain.entity.MetaDataEntity

@Entity(tableName = "meta")
data class MetaDbData(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo("totalPages")
    var totalPages: Int = 0,
    @ColumnInfo("currentPage")
    var currentPage: Int = 0,
    @ColumnInfo("nextPage")
    var nextPage: Int = 0,
    @ColumnInfo("perPage")
    var perPage: Int = 0,
    @ColumnInfo("totalCount")
    var totalCount: Int = 0
)

fun MetaDbData.toDomain() = MetaData(
    totalPages = totalPages,
    currentPage = currentPage,
    nextPage = nextPage,
    perPage = perPage,
    totalCount = totalCount
)

fun MetaDbData.toData() = MetaDataEntity(
    totalPages = totalPages,
    currentPage = currentPage,
    nextPage = nextPage,
    perPage = perPage,
    totalCount = totalCount
)