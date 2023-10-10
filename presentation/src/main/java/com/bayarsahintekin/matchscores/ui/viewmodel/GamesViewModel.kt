package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.usecase.GamesFilterUseCase
import com.bayarsahintekin.domain.usecase.GamesUseCase
import com.bayarsahintekin.domain.usecase.TeamsUseCase
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.ui.components.games.GameFilerData
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    gamesUseCase: GamesUseCase,
    gamesFilterUseCase: GamesFilterUseCase,
    teamsUseCase: TeamsUseCase,
    dispatchers: DispatchersProvider
): BaseViewModel(dispatchers) {

    private var gamesFilter = GameFilerData(listOf(), listOf())

    val games: Flow<PagingData<GameEntity>> = gamesUseCase.getGames().cachedIn(viewModelScope)

    val teams:Flow<PagingData<TeamEntity>> = teamsUseCase.getTeams().cachedIn(viewModelScope)

    val filteredGames :Flow<PagingData<GameEntity>> = gamesFilterUseCase.invoke(gamesFilter.teamIdList,gamesFilter.seasons).cachedIn(viewModelScope)


    fun onFilter(gamesFilter: GameFilerData) {
        this.gamesFilter = gamesFilter
    }
}