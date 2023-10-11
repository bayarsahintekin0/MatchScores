package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.usecase.GetPlayerListUseCase
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    private val playersUseCase: GetPlayerListUseCase,
    dispatchers: DispatchersProvider
): BaseViewModel(dispatchers) {

    val players: Flow<PagingData<PlayerEntity>> = playersUseCase.players().cachedIn(viewModelScope)
}