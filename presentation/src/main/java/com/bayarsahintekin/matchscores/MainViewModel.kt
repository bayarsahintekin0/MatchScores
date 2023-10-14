package com.bayarsahintekin.matchscores

import com.bayarsahintekin.domain.usecase.GetTeamListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTeamListUseCase: GetTeamListUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {


}