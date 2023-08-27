package com.bayarsahintekin.matchscores.ui.components.players

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayarsahintekin.matchscores.ui.viewmodel.PlayersViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayersScreen(playersViewModel: PlayersViewModel = hiltViewModel()) {

    val playersUiState = playersViewModel.uiState.collectAsState()

    playersUiState.value.data?.let {

    }

}