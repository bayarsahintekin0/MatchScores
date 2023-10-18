package com.bayarsahintekin.domain.mock

import androidx.paging.PagingData

import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.stats.StatsGameEntity
import com.bayarsahintekin.domain.entity.stats.StatsPlayerEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object StatFactory {

    fun generateDummyStatList(size: Int): Flow<PagingData<StatsEntity>> {
        val statList = mutableListOf<StatsEntity>()
        repeat(size) {
            statList.add(generateStat())
        }
        return convertToPagingDataList(statList)
    }

    private fun convertToPagingDataList(inputList: List<StatsEntity>): Flow<PagingData<StatsEntity>> {
        val pagingDataList: PagingData<StatsEntity> = PagingData.from(inputList)
        return toFlow(pagingDataList)
    }

    private fun toFlow(data: PagingData<StatsEntity>): Flow<PagingData<StatsEntity>> = flow { emit(data) }

    fun generateStat() = StatsEntity(
        id = DataFactory.getRandomInt(),
        ast = DataFactory.getRandomInt(),
        blk = DataFactory.getRandomInt(),
        dreb = DataFactory.getRandomInt(),
        fg3_pct = DataFactory.getRandomFloat(),
        fg3a = DataFactory.getRandomInt(),
        fg3m = DataFactory.getRandomInt(),
        fg_pct = DataFactory.getRandomFloat(),
        fga = DataFactory.getRandomInt(),
        fgm = DataFactory.getRandomInt(),
        ft_pct = DataFactory.getRandomFloat(),
        fta = DataFactory.getRandomInt(),
        ftm = DataFactory.getRandomInt(),
        game = StatsGameEntity(
            id = DataFactory.getRandomInt(),
            home_team_id = DataFactory.getRandomInt(),
            home_team_score = DataFactory.getRandomInt(),
            visitor_team_id = DataFactory.getRandomInt(),
            visitor_team_score = DataFactory.getRandomInt(),
            date = DataFactory.getRandomString(),
            period = DataFactory.getRandomInt(),
            postseason = DataFactory.getRandomBoolean(),
            season = DataFactory.getRandomInt(),
            status = DataFactory.getRandomString(),
            time = DataFactory.getRandomString()
        ),
        min = DataFactory.getRandomString(),
        oreb = DataFactory.getRandomInt(),
        pf = DataFactory.getRandomInt(),
        player = StatsPlayerEntity(
            id = DataFactory.getRandomInt(),
            firstName = DataFactory.getRandomString(),
            heightInches = DataFactory.getRandomString(),
            heightFeet = DataFactory.getRandomString(),
            lastName = DataFactory.getRandomString(),
            position = DataFactory.getRandomString(),
            team = DataFactory.getRandomInt(),
            weightPounds = DataFactory.getRandomString()
        ),
        pts = DataFactory.getRandomInt(),
        stl = DataFactory.getRandomInt(),
        reb = DataFactory.getRandomInt(),
        team = TeamEntity(
            id = DataFactory.getRandomInt(),
            abbreviation = DataFactory.getRandomString(),
            city = DataFactory.getRandomString(),
            conference = DataFactory.getRandomString(),
            division = DataFactory.getRandomString(),
            fullName = DataFactory.getRandomString(),
            name = DataFactory.getRandomString()
        ), turnover = DataFactory.getRandomInt()
    )
}