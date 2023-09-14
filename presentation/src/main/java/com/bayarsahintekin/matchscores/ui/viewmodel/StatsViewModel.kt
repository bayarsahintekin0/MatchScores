package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.stats.StatsListEntity
import com.bayarsahintekin.domain.usecase.StatsUseCase
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
class StatsViewModel @Inject constructor(
    dispatchers: DispatchersProvider,
    private val statsUseCase: StatsUseCase
): BaseViewModel(dispatchers) {

    var state by mutableStateOf(StatScreenState())

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

    private suspend fun getList(page: Int):List<StatsEntity> {
        getAllStats(page = page).onSuccess {
            return it.data
        }.onError {
            return emptyList()
        }
        return emptyList()
    }

    data class StatScreenState(
        val isLoading: Boolean = false,
        val items: List<StatsEntity> = emptyList(),
        val error: String? = null,
        val endReached: Boolean = false,
        val page: Int = 0
    )

    //private suspend fun getPlayers(): Flow<PagingData<PlayerEntity>> = playersUseCase.getPlayers()
    suspend fun getAllStats(page: Int): Result<StatsListEntity> = statsUseCase.invoke(page)

}