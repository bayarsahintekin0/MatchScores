package com.bayarsahintekin.matchscores.ui.components.games

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.matchscores.ui.theme.BlueGradient
import com.bayarsahintekin.matchscores.ui.theme.PinkGradient
import com.bayarsahintekin.matchscores.ui.theme.YellowGradient
import com.bayarsahintekin.matchscores.ui.viewmodel.GamesViewModel
import com.bayarsahintekin.matchscores.util.TeamLogosObject
import java.text.SimpleDateFormat

@Composable
fun GamesScreen(gamesViewModel: GamesViewModel = hiltViewModel(),onGameClicked: (id: Int) -> Unit) {

    val items = gamesViewModel.games.collectAsLazyPagingItems()
    GameListMainScreen(items = items, onGameClicked = { onGameClicked.invoke(it) })
}

@Composable
fun GameListMainScreen(
    items: LazyPagingItems<GameEntity>,
    onGameClicked: (id: Int) -> Unit
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
                    val game = items[it]
                    if (game != null) {
                        GameItem(game = game, onGameClicked = onGameClicked)
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
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                /**
                 * Visitor Team
                 */
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
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )

                /**
                 * Scoreboard
                 */
                Card(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(top = 8.dp, bottom = 8.dp),
                    elevation = 8.dp
                ) {
                    Text(
                        text = game.visitorTeamScore.toString() + " - " + game.homeTeamScore,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(4.dp)

                    )
                }
                /**
                 * Home Team
                 */
                Text(
                    text = game.homeTeam.name,
                    fontSize = 14.sp,
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