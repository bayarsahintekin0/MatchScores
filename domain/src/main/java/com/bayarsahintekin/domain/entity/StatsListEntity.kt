package com.bayarsahintekin.domain.entity

data class StatsListEntity (
    val data: List<StatsEntity>,
    val meta: MetaDataEntity?
)