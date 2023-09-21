package com.bayarsahintekin.matchscores.ui.components.stats

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayarsahintekin.matchscores.ui.viewmodel.GamesViewModel
import com.bayarsahintekin.matchscores.ui.viewmodel.StatsViewModel

@Composable
fun StatDetailScreen(
    statsViewModel: StatsViewModel = hiltViewModel(),
    statId: Int
) {
}