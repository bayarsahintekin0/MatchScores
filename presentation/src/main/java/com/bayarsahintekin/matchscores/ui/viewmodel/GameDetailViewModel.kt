package com.bayarsahintekin.matchscores.ui.viewmodel

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.usecase.GetGameByIdUseCase
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
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel  @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {
    data class GameDetailUiState(
        val isLoading: Boolean = true,
        val data: GameEntity? = null
    )

    private val _uiState = MutableStateFlow(GameDetailUiState())
    val uiState = _uiState.stateIn(viewModelScope, SharingStarted.Eagerly,_uiState.value)

    init {
        onInitialState()
    }



    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun onInitialState() = launchOnMainImmediate {
        stateHandle.get<Int>(NavigationKeys.Arg.GAME_ID)?.let {
            getGameDetail(it).onSuccess {
                val stateData = GameEntity(
                    id = it.id,
                    date = formatDate(it.date) ?: "",
                    homeTeam = it.homeTeam,
                    homeTeamScore = it.homeTeamScore,
                    period = it.period,
                    postseason = it.postseason,
                    season = it.season,
                    status = it.status,
                    time = it.time,
                    visitorTeam = it.visitorTeam,
                    visitorTeamScore = it.visitorTeamScore
                )
                _uiState.update { gameUiState ->
                    gameUiState.copy(
                        isLoading = false,
                        data = stateData
                    )
                }
            }.onError {

            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatDate(dateString: String): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd.MM.yyyy")

        val date = inputFormat.parse(dateString)
        return date?.let { outputFormat.format(it) }
    }

    private suspend fun getGameDetail(gameId: Int): Result<GameEntity> = getGameByIdUseCase.invoke(gameId = gameId)

}