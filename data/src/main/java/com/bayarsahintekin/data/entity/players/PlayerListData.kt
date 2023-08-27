package com.bayarsahintekin.data.entity.players

import com.bayarsahintekin.data.entity.MetaData
import com.bayarsahintekin.data.entity.toDomain
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.PlayerListEntity

data class PlayerListData(
    var data: List<PlayerEntity> = arrayListOf(),
    var meta: MetaData? = null
)

fun PlayerListData.toDomain() :PlayerListEntity {
    return PlayerListEntity(
            data = data.map { it },
            meta = meta?.toDomain()
        )

}
