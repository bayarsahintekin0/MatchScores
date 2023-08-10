package com.bayarsahintekin.data.entity.teams
import com.bayarsahintekin.data.entity.MetaData
import com.bayarsahintekin.data.entity.toDomain
import com.bayarsahintekin.domain.entity.TeamListEntity

data class TeamListData(
    val data: List<TeamData>,
    val meta: MetaData
)
fun TeamListData.toDomain() = TeamListEntity(
    data = data.map { it.toDomain() },
    meta = meta.toDomain()
)