package com.bayarsahintekin.data.mapper

import com.bayarsahintekin.data.entity.ListResponseDbData
import com.bayarsahintekin.data.entity.MetaDbData
import com.bayarsahintekin.data.entity.TeamsDbData
import com.bayarsahintekin.domain.entity.ListResponseEntity
import com.bayarsahintekin.domain.entity.MetaDataEntity
import com.bayarsahintekin.domain.entity.TeamEntity

fun TeamEntity.toDbData() = TeamsDbData(
    id = id,
    abbreviation = abbreviation,
    city = city,
    conference = conference,
    division = division,
    fullName = fullName,
    name = name
)

fun MetaDataEntity.toDbData() = MetaDbData(
    totalPages = totalPages,
    currentPage = currentPage,
    nextPage = nextPage,
    perPage = perPage,
    totalCount = totalCount,
)

fun ListResponseEntity.toDbData() = ListResponseDbData(
    data = (data as List<TeamEntity>).map { it.toDbData() },
    meta = meta.toDbData()
)