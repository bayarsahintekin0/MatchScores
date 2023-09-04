package com.bayarsahintekin.matchscores.ui.components.games

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayarsahintekin.domain.entity.GameEntity
import com.bayarsahintekin.domain.entity.PlayerEntity
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.components.players.PlayerLisMainScreen
import com.bayarsahintekin.matchscores.ui.theme.BlueGradient
import com.bayarsahintekin.matchscores.ui.theme.PinkGradient
import com.bayarsahintekin.matchscores.ui.theme.YellowGradient
import com.bayarsahintekin.matchscores.ui.viewmodel.GamesViewModel
import com.bayarsahintekin.matchscores.ui.viewmodel.PlayersViewModel

@Composable
fun GamesScreen(gamesViewModel: GamesViewModel = hiltViewModel()) {
    val state = gamesViewModel.state
    GameListMainScreen(state = state, gamesViewModel, onGameClicked = {

    })
}

@Composable
fun GameListMainScreen(state: GamesViewModel.GameScreenState,gamesViewModel: GamesViewModel, onGameClicked: (id: Int)-> Unit){

    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(state.items.size) { i ->
            val item = state.items[i]
            if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                gamesViewModel.loadNextItems()
            }
            GameItem(game = item, onGameClicked = onGameClicked)
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameItem(game: GameEntity, onGameClicked: (id: Int)-> Unit){
    Card(modifier = Modifier.padding(4.dp),
        onClick = {
            game.id.let { onGameClicked.invoke(it) }
        },
        border = BorderStroke(1.dp, Brush.horizontalGradient(
            arrayListOf(BlueGradient, YellowGradient, PinkGradient)
        ))
    ) {
        Row(Modifier.fillMaxSize()) {
            Text(text = game.visitorTeam.name)
            Text(text = game.visitorTeamScore.toString() + " - " + game.homeTeamScore,
            modifier = Modifier.border(
                BorderStroke(1.dp, Brush.horizontalGradient(
                    arrayListOf(BlueGradient, YellowGradient, PinkGradient)
            ))))
            Text(text = game.homeTeam.name)
        }
    }
}