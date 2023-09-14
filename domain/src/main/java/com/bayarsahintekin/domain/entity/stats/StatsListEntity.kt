package com.bayarsahintekin.domain.entity.stats

import com.bayarsahintekin.domain.entity.MetaDataEntity

data class StatsListEntity (
    val data: List<StatsEntity>,
    val meta: MetaDataEntity?
)