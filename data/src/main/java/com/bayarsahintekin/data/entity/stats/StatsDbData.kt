package com.bayarsahintekin.data.entity.stats

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.stats.StatsGameEntity
import com.bayarsahintekin.domain.entity.stats.StatsPlayerEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity

@Entity(tableName = "stats")
data class StatsDbData(
    @PrimaryKey var id: Int = 0,
    val ast: Int = 0,
    val blk: Int = 0,
    val dreb: Int = 0,
    val fg3_pct: Float = 0f,
    val fg3a: Int = 0,
    val fg3m: Int = 0,
    val fg_pct: Float = 0f,
    val fga: Int = 0,
    val fgm: Int = 0,
    val ft_pct: Float = 0f,
    val fta: Int = 0,
    val ftm: Int = 0,
    val game: StatsGameEntity = StatsGameEntity(0,"", 0,
        0,0,false,0,"","", 0,
        0),
    val min: String = "",
    val oreb: Int = 0,
    val pf: Int = 0,
    val player: StatsPlayerEntity = StatsPlayerEntity(0,"","","","","", 0,
    ""),
    val pts: Int = 0,
    val reb: Int = 0,
    val stl: Int = 0,
    val team: TeamEntity = TeamEntity(0,"","","","","",""),
    val turnover: Int = 0,

    )

fun StatsDbData.toDomain() = StatsEntity(
    id = id,
    ast = ast,
    blk = blk,
    dreb = dreb,
    fg3_pct = fg3_pct,
    fg3a = fg3a,
    fg3m = fg3m,
    fg_pct = fg_pct,
    fga = fga,
    fgm = fgm,
    ft_pct = ft_pct,
    fta = fta,
    ftm = ftm,
    game = game,
    min = min,
    oreb = oreb,
    pf = pf,
    player = player,
    pts = pts,
    reb = reb,
    stl = stl,
    team = team,
    turnover = turnover
)