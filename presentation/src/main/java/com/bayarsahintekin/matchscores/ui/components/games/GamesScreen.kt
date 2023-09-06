package com.bayarsahintekin.matchscores.ui.components.games

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayarsahintekin.domain.entity.GameEntity
import com.bayarsahintekin.matchscores.ui.theme.BlueGradient
import com.bayarsahintekin.matchscores.ui.theme.PinkGradient
import com.bayarsahintekin.matchscores.ui.theme.YellowGradient
import com.bayarsahintekin.matchscores.ui.viewmodel.GamesViewModel
import com.bayarsahintekin.matchscores.util.TeamLogosObject
import java.text.SimpleDateFormat

@Composable
fun GamesScreen(gamesViewModel: GamesViewModel = hiltViewModel()) {
    val state = gamesViewModel.state
    GameListMainScreen(state = state, gamesViewModel, onGameClicked = {

    })
}

@Composable
fun GameListMainScreen(
    state: GamesViewModel.GameScreenState,
    gamesViewModel: GamesViewModel,
    onGameClicked: (id: Int) -> Unit
) {

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
fun GameItem(game: GameEntity, onGameClicked: (id: Int) -> Unit) {
    val formattedDate = formatDate(game.date)
    Card(
        modifier = Modifier.padding(4.dp),
        onClick = {
            game.id.let { onGameClicked.invoke(it) }
        },
        border = BorderStroke(
            1.dp, Brush.horizontalGradient(
                arrayListOf(BlueGradient, YellowGradient, PinkGradient)
            )
        )
    ) {
        Column() {
            formattedDate?.let { Text(text = it, modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(top = 8.dp)
            ) }
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
                Image(
                    painter = painterResource(id = TeamLogosObject.getTeamLogo(game.visitorTeam.abbreviation)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .fillMaxSize()
                        .padding(start = 4.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = game.visitorTeam.name,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
                Card(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(top = 8.dp, bottom = 8.dp),
                ) {
                    Text(
                        text = game.visitorTeamScore.toString() + " - " + game.homeTeamScore,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(4.dp)

                    )
                }
                Text(
                    text = game.homeTeam.name,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
                Image(
                    painter = painterResource(id = TeamLogosObject.getTeamLogo(game.homeTeam.abbreviation)),
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                        .fillMaxSize()
                        .padding(end = 4.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
        
    }
}

@SuppressLint("SimpleDateFormat")
fun formatDate(dateString: String): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val outputFormat = SimpleDateFormat("dd.MM.yyyy")

    val date = inputFormat.parse(dateString)
    return date?.let { outputFormat.format(it) }
}