package com.bayarsahintekin.data.entity

import com.bayarsahintekin.domain.entity.ListResponseEntity

data class ListResponse(
    val data: List<TeamData>,
    val meta: MetaData
)
fun ListResponse.toDomain() = ListResponseEntity(
    data = data.map { it.toDomain() },
    meta = meta.toDomain()
)