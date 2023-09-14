package com.bayarsahintekin.domain.entity.teams

import com.bayarsahintekin.domain.entity.MetaDataEntity

data class TeamListEntity(
    val data: List<TeamEntity>,
    val meta: MetaDataEntity
)