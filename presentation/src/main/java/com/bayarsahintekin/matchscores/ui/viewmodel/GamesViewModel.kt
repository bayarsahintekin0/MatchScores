package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.bayarsahintekin.domain.entity.GameEntity
import com.bayarsahintekin.domain.entity.GameListEntity
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.domain.entity.PlayerListEntity
import com.bayarsahintekin.domain.usecase.GamesUseCase
import com.bayarsahintekin.domain.utils.Result
import com.bayarsahintekin.domain.utils.onError
import com.bayarsahintekin.domain.utils.onSuccess
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DefaultPaginator
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val gamesUseCase: GamesUseCase,
    dispatchers: DispatchersProvider
): BaseViewModel(dispatchers) {

    var state by mutableStateOf(GameScreenState())

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

    private suspend fun getList(page: Int):List<GameEntity> {
        getAllGames(page = page).onSuccess {
            return it.data
        }.onError {
            return emptyList()
        }
        return emptyList()
    }

    data class GameScreenState(
        val isLoading: Boolean = false,
        val items: List<GameEntity> = emptyList(),
        val error: String? = null,
        val endReached: Boolean = false,
        val page: Int = 0
    )

    //private suspend fun getPlayers(): Flow<PagingData<PlayerEntity>> = playersUseCase.getPlayers()
    suspend fun getAllGames(page: Int): Result<GameListEntity> = gamesUseCase.invoke(page)
}