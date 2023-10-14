package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.usecase.GetPlayerByIdUseCase
import com.bayarsahintekin.domain.usecase.GetTeamByIdUseCase
import com.bayarsahintekin.domain.usecase.StatsUseCase
import com.bayarsahintekin.domain.utils.Result
import com.bayarsahintekin.domain.utils.onSuccess
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.ui.components.NavigationKeys
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val getPlayerByIdUseCase: GetPlayerByIdUseCase,
    private val getTeamByIdUseCase: GetTeamByIdUseCase,
    private val statsUseCase: StatsUseCase,
    dispatchers: DispatchersProvider
): BaseViewModel(dispatchers) {

    private suspend fun getPlayerById(playerId: String): Result<PlayerEntity> = getPlayerByIdUseCase.invoke(playerId = playerId)

    data class PlayerDetailsUiState(
        val firstName: String? = "",
        val lastName: String? = "",
        val position: String? = "",
        val teamFullName: String? = "",
        val teamShortName: String? = "",
        val teamAbbreviation: String? = "",
        val heightInches: Float? = 0f,
        val weightPounds: Float? = 0f

    )

    fun getStats(season:Int? = null, playerId: Int? = stateHandle.get<Int>(NavigationKeys.Arg.PLAYER_ID)): Flow<PagingData<StatsEntity>> =
        statsUseCase.invoke(season,playerId).cachedIn(viewModelScope)

    suspend fun getTeamById(teamId: String): Result<TeamEntity> = getTeamByIdUseCase.invoke(teamId)

    private val _uiState: MutableStateFlow<PlayerDetailsUiState> = MutableStateFlow(PlayerDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        onInitialState()
    }


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onInitialState() = launchOnMainImmediate {
        stateHandle.get<Int>(NavigationKeys.Arg.PLAYER_ID)?.let {
            getPlayerById(playerId = it.toString()).onSuccess {
                _uiState.value = PlayerDetailsUiState(
                    firstName = it.firstName,
                    lastName = it.lastName,
                    position = it.position,
                    teamFullName = it.team?.fullName,
                    teamShortName = it.team?.name,
                    teamAbbreviation = it.team?.abbreviation,
                    heightInches = it.heightInches?.toFloat(),
                    weightPounds = it.weightPounds?.toFloat()

                )
            }
        }
    }


}
