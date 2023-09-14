package com.bayarsahintekin.matchscores.ui.components.players

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.theme.BlueGradient
import com.bayarsahintekin.matchscores.ui.theme.PinkGradient
import com.bayarsahintekin.matchscores.ui.theme.YellowGradient
import com.bayarsahintekin.matchscores.ui.viewmodel.PlayersViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun PlayersScreen(playersViewModel: PlayersViewModel = hiltViewModel()) {

    val items = playersViewModel.players.collectAsLazyPagingItems()
    PlayerLisMainScreen(items, playersViewModel,onPlayerClicked = {})

}

@Composable
fun PlayerLisMainScreen(items: LazyPagingItems<PlayerEntity>, playersViewModel: PlayersViewModel, onPlayerClicked: (id: Int)-> Unit){

    Box(modifier = Modifier.fillMaxSize()) {
        if (items.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 120.dp),
                contentPadding = PaddingValues(8.dp),

                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(count = items.itemCount,
                    key = items.itemKey { it.id!! }
                ) {
                    val player = items[it]
                    if (player != null) {
                        PlayerItem(player = player, onPlayerClicked = onPlayerClicked)
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
fun PlayerItem(player: PlayerEntity, onPlayerClicked: (id: Int)-> Unit){
    Card(modifier = Modifier.padding(4.dp),
        onClick = {
            player.id?.let { onPlayerClicked.invoke(it) }
        },
        border = BorderStroke(1.dp, Brush.horizontalGradient(
            arrayListOf(BlueGradient, YellowGradient, PinkGradient)
        ))
    ) {
        Column(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_player_holder),
                contentDescription = player.firstName,
                modifier = Modifier
                    .height(80.dp)
                    .padding(top = 8.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Text(
                text = player.firstName + " " + player.lastName,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.position) + ": " + player.position ,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.team) + ": " +player.team?.name,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
        }
    }
}