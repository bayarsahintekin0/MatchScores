package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.usecase.GamesFilterUseCase
import com.bayarsahintekin.domain.usecase.GamesUseCase
import com.bayarsahintekin.domain.usecase.TeamsUseCase
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gamesUseCase: GamesUseCase,
    private val gamesFilterUseCase: GamesFilterUseCase,
    teamsUseCase: TeamsUseCase,
    dispatchers: DispatchersProvider
): BaseViewModel(dispatchers) {

    val games: Flow<PagingData<GameEntity>> = gamesUseCase.getGames().cachedIn(viewModelScope)

    val teams:Flow<PagingData<TeamEntity>> = teamsUseCase.getTeams().cachedIn(viewModelScope)

    //val gamesFilter(teams:List<Int>,seasons:List<Int>) :Flow<PagingData<GameEntity>> = gamesFilterUseCase.invoke(teams,seasons).cachedIn(viewModelScope)
}