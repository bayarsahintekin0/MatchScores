package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import com.bayarsahintekin.domain.entity.ListResponseEntity
import com.bayarsahintekin.domain.entity.TeamEntity
import com.bayarsahintekin.domain.usecase.TeamUseCase
import com.bayarsahintekin.domain.usecase.TeamsUseCase
import com.bayarsahintekin.domain.utils.Result
import com.bayarsahintekin.domain.utils.onError
import com.bayarsahintekin.domain.utils.onSuccess
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val teamUseCase: TeamUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    data class TeamDetailUiState(
        val isLoading: Boolean = true,
        val data: TeamEntity? = null
    )

    private val _uiState = MutableStateFlow(TeamDetailUiState())
    val uiState = _uiState.stateIn(viewModelScope, SharingStarted.Eagerly,_uiState.value)


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onInitialState(teamId: String) = launchOnMainImmediate {

        getTeamDetail(teamId).onSuccess {
            val stateData: TeamEntity

            val teamsData = arrayListOf<TeamEntity>()

            stateData = TeamEntity(
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

    suspend fun getTeamDetail(teamId: String): Result<TeamEntity> = teamUseCase.invoke(teamId = teamId)

}