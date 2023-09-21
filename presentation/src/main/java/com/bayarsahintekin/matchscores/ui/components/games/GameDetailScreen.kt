package com.bayarsahintekin.matchscores.ui.components.games

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayarsahintekin.matchscores.ui.viewmodel.GamesViewModel

@Composable
fun GameDetailScreen(
    teamDetailViewModel: GamesViewModel = hiltViewModel(),
    gameId: Int
) {
}