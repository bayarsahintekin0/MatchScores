package com.bayarsahintekin.domain.entity.players

import com.bayarsahintekin.domain.entity.MetaDataEntity

data class PlayerListEntity(
    val data: List<PlayerEntity>,
    val meta: MetaDataEntity?
)