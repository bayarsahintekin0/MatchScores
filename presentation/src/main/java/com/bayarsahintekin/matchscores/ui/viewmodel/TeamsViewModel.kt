package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.usecase.GetTeamListUseCase
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getTeamListUseCase: GetTeamListUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {

    val teams: Flow<PagingData<TeamEntity>> = getTeamListUseCase.invoke().cachedIn(viewModelScope)

}