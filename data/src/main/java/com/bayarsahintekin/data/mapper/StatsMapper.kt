package com.bayarsahintekin.data.mapper

import com.bayarsahintekin.data.entity.stats.StatsDbData
import com.bayarsahintekin.domain.entity.StatsEntity

fun StatsEntity.toDbData() = StatsDbData(
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