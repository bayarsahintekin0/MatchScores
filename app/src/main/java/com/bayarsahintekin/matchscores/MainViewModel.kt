package com.bayarsahintekin.matchscores

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.bayarsahintekin.domain.entity.ListResponseEntity
import com.bayarsahintekin.domain.entity.TeamEntity
import com.bayarsahintekin.domain.usecase.TeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.bayarsahintekin.domain.utils.Result
import com.bayarsahintekin.domain.utils.onError
import com.bayarsahintekin.domain.utils.onSuccess
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModel @Inject constructor(
    private val teamsUseCase: TeamsUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    data class TeamsUiState(
        val id: Int = 0,
        val abbreviation: String = "",
        val city: String = "",
        val conference: String = "",
        val division: String = "",
        val fullName: String = "",
        val name: String = ""
    )

    private val _uiState: MutableStateFlow<ListResponseEntity>? = null
    val uiState = _uiState?.asStateFlow()

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
                teamsData.add(TeamEntity(
                    id = i.id,
                    abbreviation = i.abbreviation,
                    city = i.city,
                    conference = i.conference,
                    division = i.division,
                    fullName = i.fullName,
                    name = i.name
                ))
            }
            stateData = ListResponseEntity(data = teamsData, meta = it.meta)
            _uiState?.value = stateData
        }.onError {

        }
    }

    private suspend fun getTeams(): Result<ListResponseEntity> = teamsUseCase.invoke()

}