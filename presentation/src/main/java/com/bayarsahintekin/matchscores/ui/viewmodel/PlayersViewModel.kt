package com.bayarsahintekin.matchscores.ui.viewmodel

import com.bayarsahintekin.matchscores.ui.base.BaseViewModel
import com.bayarsahintekin.matchscores.util.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayersViewModel @Inject constructor(
    dispatchers: DispatchersProvider
): BaseViewModel(dispatchers) {

}