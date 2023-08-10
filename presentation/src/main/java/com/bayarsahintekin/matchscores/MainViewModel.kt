package com.bayarsahintekin.matchscores

import com.bayarsahintekin.domain.usecase.TeamsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider

@HiltViewModel
class MainViewModel @Inject constructor(
    private val teamsUseCase: TeamsUseCase,
    dispatchers: DispatchersProvider
) : BaseViewModel(dispatchers) {


}