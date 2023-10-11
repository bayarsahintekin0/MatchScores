package com.bayarsahintekin.matchscores.ui.components.stats

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.components.players.PlayerItem
import com.bayarsahintekin.matchscores.ui.theme.BlueGradient
import com.bayarsahintekin.matchscores.ui.theme.PinkGradient
import com.bayarsahintekin.matchscores.ui.theme.YellowGradient
import com.bayarsahintekin.matchscores.ui.viewmodel.StatsViewModel

@Composable
fun StatsScreen(statsViewModel: StatsViewModel = hiltViewModel(), onStatClicked: (id: Int) -> Unit) {

    val items = statsViewModel.getStats().collectAsLazyPagingItems()
    StatListMainScreen(items = items, onStatClicked = { onStatClicked.invoke(it) })
}

@Composable
fun StatListMainScreen(
    items: LazyPagingItems<StatsEntity>,
    onStatClicked: (id: Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (items.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(count = items.itemCount,
                    key = items.itemKey { it.id!! }
                ) {
                    val stat = items[it]
                    if (stat != null) {
                        StatItem(stat = stat, onStatClicked = onStatClicked)
                    }
                }

                item {
                    if (items.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatItem(stat: StatsEntity, onStatClicked: (id: Int) -> Unit) {
    Card(
        modifier = Modifier.padding(4.dp),
        onClick = {
            stat.id.let { it?.let { it1 -> onStatClicked.invoke(it1) } }
        },
        border = BorderStroke(
            1.dp, Brush.horizontalGradient(
                arrayListOf(BlueGradient, YellowGradient, PinkGradient)
            )
        )
    ) {
        Row(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_player_holder),
                contentDescription = stat.id.toString(),
                modifier = Modifier
                    .height(120.dp)
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = stringResource(id = R.string.name) + ": " + stat.player.firstName + " " + stat.player.lastName,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = stringResource(id = R.string.points) + ": " + stat.pts.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier

                        .padding(top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = stringResource(id = R.string.asists) + ": " + stat.ast.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier

                        .padding(top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = stringResource(id = R.string.rebounds) + ": " + stat.reb.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier

                        .padding(top = 4.dp, bottom = 4.dp)
                )
                Text(
                    text = stringResource(id = R.string.trnover) + ": " + stat.turnover.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                )
            }

        }
    }
}
