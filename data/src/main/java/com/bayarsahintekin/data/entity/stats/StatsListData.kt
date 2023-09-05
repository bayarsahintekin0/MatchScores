package com.bayarsahintekin.data.entity.stats

import com.bayarsahintekin.data.entity.MetaData
import com.bayarsahintekin.data.entity.toDomain
import com.bayarsahintekin.domain.entity.StatsListEntity

data class StatsListData(
    val data: List<StatsData> = arrayListOf(),
    val meta: MetaData? = null
)

fun StatsListData.toDomain() : StatsListEntity {
    return StatsListEntity(
        data = data.map { it.toDomain() },
        meta = meta?.toDomain()
    )
}