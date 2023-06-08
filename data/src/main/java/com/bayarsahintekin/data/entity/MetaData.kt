package com.bayarsahintekin.data.entity

import com.bayarsahintekin.domain.entity.MetaDataEntity
import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("total_pages") var totalPages: Int,
    @SerializedName("current_page") var currentPage: Int,
    @SerializedName("next_page") var nextPage: Int,
    @SerializedName("per_page") var perPage: Int,
    @SerializedName("total_count") var totalCount: Int
)

fun MetaData.toDomain() = MetaDataEntity(
    totalPages = totalPages,
    currentPage = currentPage,
    nextPage = nextPage,
    perPage = perPage,
    totalCount = totalCount
)