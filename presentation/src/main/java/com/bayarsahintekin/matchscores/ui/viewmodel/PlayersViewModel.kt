package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.usecase.GetPlayerListUseCase
import com.bayarsahintekin.domain.usecase.SearchPlayerUseCase
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val playersUseCase: GetPlayerListUseCase,
    private val searchPlayerUseCase: SearchPlayerUseCase,
    dispatchers: DispatchersProvider
): BaseViewModel(dispatchers) {

    private val _playersState: MutableStateFlow<PagingData<PlayerEntity>> = MutableStateFlow(PagingData.empty())
    val playersState = _playersState.asStateFlow()

    fun searchPlayer(query: String)  = launchOnMainImmediate{
        val result = searchPlayerUseCase.invoke(query).cachedIn(viewModelScope)
        result.collect{
            _playersState.value = it
        }
    }

    init {
        onInitialState()
    }


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun onInitialState() = launchOnMainImmediate {
        val result = playersUseCase.invoke().cachedIn(viewModelScope)
        result.collect{
            _playersState.value = it
        }
    }
}