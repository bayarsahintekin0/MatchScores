package com.bayarsahintekin.domain.entity

data class MetaDataEntity(
    val totalPages: Int,
    val currentPage: Int,
    val nextPage: Int,
    val perPage: Int,
    val totalCount: Int,
)