package com.bayarsahintekin.matchscores.ui.components.games

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.theme.BlueGradient
import com.bayarsahintekin.matchscores.ui.theme.PinkGradient
import com.bayarsahintekin.matchscores.ui.theme.YellowGradient
import com.bayarsahintekin.matchscores.ui.viewmodel.GamesViewModel
import com.bayarsahintekin.matchscores.util.TeamLogosObject
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@Composable
fun GamesScreen(
    gamesViewModel: GamesViewModel = hiltViewModel(),
    onGameClicked: (id: Int) -> Unit
) {

    val items = gamesViewModel.gamesState.collectAsLazyPagingItems()
    val teamItems = gamesViewModel.teams.collectAsLazyPagingItems()

    TeamsBottomSheet(gamesViewModel,teamItems, items, onGameClicked)
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
            formattedDate?.let {
                Text(
                    text = it, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp)
                )
            }
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TeamsBottomSheet(
    viewModel: GamesViewModel,
    teamItems: LazyPagingItems<TeamEntity>,
    items: LazyPagingItems<GameEntity>,
    onGameClicked: (id: Int) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()
    val selectedFilterTeam = remember { mutableStateOf("") }
    val selectedFilterSeason = remember { mutableStateOf("") }

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            TabScreen(teamItems,
                onFilterApplied = {
                    coroutineScope.launch { sheetState.hide() }
                    viewModel.filterGames(it.selectedTeamId,it.selectedSeason)
                    if (it.selectedTeamId == null)
                        selectedFilterTeam.value = ""
                    else{
                        viewModel.getTeamNameById(it.selectedTeamId){
                            selectedFilterTeam.value = "Team: ${it}"
                        }
                    }

                    if (it.selectedSeason == null)
                        selectedFilterSeason.value = ""
                    else
                        selectedFilterSeason.value = "Season: ${it.selectedSeason}"
                })
            coroutineScope.launch { sheetState.hide() }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Column (modifier = Modifier.align(Alignment.CenterVertically)){
                    Text(text = selectedFilterTeam.value)
                    Text(text = selectedFilterSeason.value)
                }
                Image(painter = painterResource(id = R.drawable.ic_filter), contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                        .padding(top = 8.dp, end = 8.dp)
                        .clickable {
                            coroutineScope.launch {
                                if (sheetState.isVisible) sheetState.hide()
                                else sheetState.show()
                            }
                        })


            }
            Spacer(modifier = Modifier.height(4.dp))
            GameListMainScreen(items = items, onGameClicked = { onGameClicked.invoke(it) })
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