package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.annotation.MainThread
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.usecase.PlayersUseCase
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val playersUseCase: PlayersUseCase,
    dispatchers: DispatchersProvider
): BaseViewModel(dispatchers) {

    data class PlayersUiState(
        val isLoading: Boolean = true,
        val data: PlayerListEntity? = null,
        val title: String = "Players"
    )

    private val _uiState = MutableStateFlow(PlayersUiState())
    val uiState = _uiState.stateIn(viewModelScope, SharingStarted.Eagerly,_uiState.value)



    init {
        fetchData(20)
    }




    private fun fetchData(page: Int) =launchOnMainImmediate {
        getAllPlayers(page).onSuccess {
            val stateData: PlayerListEntity

            val playersData = arrayListOf<PlayerEntity>()
            for(i in it.data){
                playersData.add(
                    PlayerEntity(
                        id = i?.id,
                        firstName = i?.firstName,
                        heightFeet = i?.heightFeet,
                        heightInches = i?.heightInches,
                        lastName = i?.lastName,
                        position = i?.position,
                        team = i?.team,
                        weightPounds = i?.weightPounds
                    )
                )
            }
            stateData = PlayerListEntity(data = playersData, meta = it.meta)
            _uiState.update { teamsUiState ->
                teamsUiState.copy(
                    isLoading = false,
                    data = stateData
                )
            }
        }.onError {

        }
    }
    private suspend fun getPlayers(page: Int): Result<PlayerListEntity> = playersUseCase.invoke(page)
    private suspend fun getAllPlayers(page: Int): Result<PlayerListEntity> = playersUseCase.invoke(page)

}