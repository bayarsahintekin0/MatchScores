package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.usecase.PlayersUseCase
import com.bayarsahintekin.domain.utils.Result
import com.bayarsahintekin.domain.utils.onError
import com.bayarsahintekin.domain.utils.onSuccess
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DefaultPaginator
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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

    private val _dataStateFlow = MutableStateFlow(PlayersUiState())
    val dataStateFlow: StateFlow<PlayersUiState> get() = _dataStateFlow

    val players = playersUseCase.getPlayers()



    var state by mutableStateOf(ScreenState())

    private val paginator = DefaultPaginator(
        initialKey = state.page,
        onRequest = {
            getList(it)
        },
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onSuccess = { items, newKey ->
            state = state.copy(
                items = state.items + items,
                page = newKey,
                endReached = items.isEmpty()
            )
        },
        onError = {
            state = state.copy(
                error = it?.localizedMessage
            )
        },
        getNextKey = {
            state.page + 1
        }
    )


    init {
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }


    val playersData = arrayListOf<PlayerEntity>()

    private suspend fun getList(page: Int):List<PlayerEntity> {
        getAllPlayers(page = page).onSuccess {
            return it.data
        }.onError {
            return emptyList()
        }
        return emptyList()
    }
    fun fetchData(page: Int) =launchOnMainImmediate {
        getAllPlayers(page).onSuccess {

            for(i in it.data){
                playersData.add(
                    PlayerEntity(
                        id = i.id,
                        firstName = i.firstName,
                        heightFeet = i.heightFeet,
                        heightInches = i.heightInches,
                        lastName = i.lastName,
                        position = i.position,
                        team = i.team,
                        weightPounds = i.weightPounds
                    )
                )
            }
            val stateData = PlayerListEntity(data = playersData, meta = it.meta)

            _dataStateFlow.value = PlayersUiState(isLoading = false,data = stateData)
            /*_uiState.update { teamsUiState ->
                teamsUiState.copy(
                    isLoading = false,
                    data = stateData
                )
            }*/
        }.onError {

        }
    }

    data class ScreenState(
        val isLoading: Boolean = false,
        val items: List<PlayerEntity> = emptyList(),
        val error: String? = null,
        val endReached: Boolean = false,
        val page: Int = 0
    )

    sealed class ViewState {
        object EmptyScreen : ViewState()
        data class Loaded(val data: PlayerListEntity? = null, val loadingMore: Boolean) : ViewState()
        object Loading : ViewState()
    }
    //private suspend fun getPlayers(): Flow<PagingData<PlayerEntity>> = playersUseCase.getPlayers()
    suspend fun getAllPlayers(page: Int): Result<PlayerListEntity> = playersUseCase.invoke(page)

}