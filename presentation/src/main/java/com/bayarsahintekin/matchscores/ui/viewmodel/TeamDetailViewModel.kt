package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.usecase.GetTeamByIdUseCase
import com.bayarsahintekin.domain.utils.Result
import com.bayarsahintekin.domain.utils.onError
import com.bayarsahintekin.domain.utils.onSuccess
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.ui.components.NavigationKeys
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val getTeamByIdUseCase: GetTeamByIdUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    data class TeamDetailUiState(
        val isLoading: Boolean = true,
        val data: TeamEntity? = null
    )

    private val _uiState = MutableStateFlow(TeamDetailUiState())
    val uiState = _uiState.stateIn(viewModelScope, SharingStarted.Eagerly,_uiState.value)

    init {
        onInitialState()
    }


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun onInitialState() = launchOnMainImmediate {
        getTeamDetail(stateHandle.get<Int>(NavigationKeys.Arg.TEAM_ID).toString()).onSuccess {
            val stateData = TeamEntity(
                id = it.id,
                abbreviation = it.abbreviation,
                city = it.city,
                conference = it.conference,
                division = it.division,
                fullName = it.fullName,
                name = it.name
            )
            _uiState.update { teamsUiState ->
                teamsUiState.copy(
                    isLoading = false,
                    data = stateData
                )
            }
        }.onError {

        }
    }

    private suspend fun getTeamDetail(teamId: String): Result<TeamEntity> = getTeamByIdUseCase.invoke(teamId = teamId)

}