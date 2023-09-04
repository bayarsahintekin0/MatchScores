package com.bayarsahintekin.matchscores.ui.components.stats

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayarsahintekin.domain.entity.GameEntity
import com.bayarsahintekin.domain.entity.StatsEntity
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.theme.BlueGradient
import com.bayarsahintekin.matchscores.ui.theme.PinkGradient
import com.bayarsahintekin.matchscores.ui.theme.YellowGradient
import com.bayarsahintekin.matchscores.ui.viewmodel.GamesViewModel
import com.bayarsahintekin.matchscores.ui.viewmodel.StatsViewModel

@Composable
fun StatsScreen(statsViewModel: StatsViewModel = hiltViewModel()) {
    val state = statsViewModel.state
    StatListMainScreen(state = state, statsViewModel, onStatClicked = {

    })
}

@Composable
fun StatListMainScreen(
    state: StatsViewModel.StatScreenState,
    statsViewModel: StatsViewModel,
    onStatClicked: (id: Int) -> Unit
) {

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(state.items.size) { i ->
            val item = state.items[i]
            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                statsViewModel.loadNextItems()
            }
            StatItem(stat = item, onGameClicked = onStatClicked)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatItem(stat: StatsEntity, onGameClicked: (id: Int) -> Unit) {
    Card(
        modifier = Modifier.padding(4.dp),
        onClick = {
            stat.id.let { onGameClicked.invoke(it) }
        },
        border = BorderStroke(
            1.dp, Brush.horizontalGradient(
                arrayListOf(BlueGradient, YellowGradient, PinkGradient)
            )
        )
    ) {

    }
}
