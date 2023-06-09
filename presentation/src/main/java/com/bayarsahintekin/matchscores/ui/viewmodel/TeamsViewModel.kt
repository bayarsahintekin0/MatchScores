package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import com.bayarsahintekin.domain.entity.ListResponseEntity
import com.bayarsahintekin.domain.entity.TeamEntity
import com.bayarsahintekin.domain.usecase.TeamsUseCase
import com.bayarsahintekin.domain.utils.Result
import com.bayarsahintekin.domain.utils.onError
import com.bayarsahintekin.domain.utils.onSuccess
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val teamsUseCase: TeamsUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {


    data class TeamsUiState(
        val isLoading: Boolean = true,
        val data: ListResponseEntity? = null
    )

    private val _uiState = MutableStateFlow(TeamsUiState())
    val uiState = _uiState.stateIn(viewModelScope, SharingStarted.Eagerly,_uiState.value)

    init {
        onInitialState()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onInitialState() = launchOnMainImmediate {

        getTeams().onSuccess {
            val stateData: ListResponseEntity
            val a = it
            val da = it

            val teamsData = arrayListOf<TeamEntity>()
            for(i in it.data){
                teamsData.add(
                    TeamEntity(
                    id = i.id,
                    abbreviation = i.abbreviation,
                    city = i.city,
                    conference = i.conference,
                    division = i.division,
                    fullName = i.fullName,
                    name = i.name
                )
                )
            }
            stateData = ListResponseEntity(data = teamsData, meta = it.meta)
            _uiState.update { teamsUiState ->
                teamsUiState.copy(
                    isLoading = false,
                    data = stateData
                )
            }
        }.onError {

        }
    }

     suspend fun getTeams(): Result<ListResponseEntity> = teamsUseCase.invoke()

}