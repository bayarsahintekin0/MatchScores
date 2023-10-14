package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.usecase.FilterGamesUseCase
import com.bayarsahintekin.domain.usecase.GetGameListUseCase
import com.bayarsahintekin.domain.usecase.GetTeamByIdUseCase
import com.bayarsahintekin.domain.usecase.GetTeamListUseCase
import com.bayarsahintekin.domain.utils.onSuccess
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.ui.components.games.GameFilerData
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGameListUseCase: GetGameListUseCase,
    private val filterGamesUseCase: FilterGamesUseCase,
    private val getTeamByIdUseCase: GetTeamByIdUseCase,
    getTeamListUseCase: GetTeamListUseCase,
    dispatchers: DispatchersProvider
): BaseViewModel(dispatchers) {
    val teams:Flow<PagingData<TeamEntity>> = getTeamListUseCase.invoke().cachedIn(viewModelScope)

    private val _gamesState: MutableStateFlow<PagingData<GameEntity>> = MutableStateFlow(PagingData.empty())
    val gamesState = _gamesState.asStateFlow()

    fun filterGames(selectedTeamId: Int? = null, selectedSeason: Int? = null)  = launchOnMainImmediate{
        val result = filterGamesUseCase.invoke(selectedTeamId,selectedSeason).cachedIn(viewModelScope)
        result.collect{
            _gamesState.value = it
        }
    }

    init {
        onInitialState()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onInitialState() = launchOnMainImmediate {
        val result = getGameListUseCase.getGames().cachedIn(viewModelScope)
        result.collect{
            _gamesState.value = it
        }
    }

    fun getTeamNameById(teamId: Int, onTeamName: (teamName: String) -> Unit) = viewModelScope.launch {
            getTeamByIdUseCase.invoke(teamId.toString()).onSuccess {
                onTeamName.invoke(it.fullName)
            }
        }

}