package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.usecase.FilterGamesUseCase
import com.bayarsahintekin.domain.usecase.GetGameListUseCase
import com.bayarsahintekin.domain.usecase.GetTeamListUseCase
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.ui.components.games.GameFilerData
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    getGameListUseCase: GetGameListUseCase,
    filterGamesUseCase: FilterGamesUseCase,
    getTeamListUseCase: GetTeamListUseCase,
    dispatchers: DispatchersProvider
): BaseViewModel(dispatchers) {

    private var gamesFilter = GameFilerData(listOf(), listOf())

    val games: Flow<PagingData<GameEntity>> = getGameListUseCase.getGames().cachedIn(viewModelScope)

    val teams:Flow<PagingData<TeamEntity>> = getTeamListUseCase.invoke().cachedIn(viewModelScope)

    val filteredGames :Flow<PagingData<GameEntity>> = filterGamesUseCase.invoke(gamesFilter.teamIdList,gamesFilter.seasons).cachedIn(viewModelScope)


    fun onFilter(gamesFilter: GameFilerData) {
        this.gamesFilter = gamesFilter
    }
}