package com.bayarsahintekin.domain.entity

data class ListResponseEntity(
    val data: List<TeamEntity>,
    val meta: MetaDataEntity
)