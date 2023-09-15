package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.usecase.StatsUseCase
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    dispatchers: DispatchersProvider,
    private val statsUseCase: StatsUseCase
): BaseViewModel(dispatchers) {
    val stats: Flow<PagingData<StatsEntity>> = statsUseCase.getStats().cachedIn(viewModelScope)
}