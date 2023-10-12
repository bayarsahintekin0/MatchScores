package com.bayarsahintekin.matchscores.ui.components.players

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
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
import com.bayarsahintekin.matchscores.util.TeamLogosObject

@SuppressLint("SuspiciousIndentation")
@Composable
fun PlayersScreen(
    playersViewModel: PlayersViewModel = hiltViewModel(),
    onPlayerClicked: (id: Int) -> Unit
) {

    val items = playersViewModel.playersState.collectAsLazyPagingItems()

    PlayerLisMainScreen(
        items = items,
        onPlayerClicked = { onPlayerClicked.invoke(it) },
        onSearchApplied = {
            playersViewModel.searchPlayer(it)
        }
    )
}

@Composable
fun PlayerLisMainScreen(
    items: LazyPagingItems<PlayerEntity>,
    onPlayerClicked: (id: Int) -> Unit,
    onSearchApplied: (query: String) -> Unit
) {

    Column {
        SearchBarView(onSearchApplied)
        Spacer(modifier = Modifier.height(4.dp))
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
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(32.dp)
                            )
                        }
                    }
                }
            }
        }
    }

}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerItem(player: PlayerEntity, onPlayerClicked: (id: Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        onClick = {
            player.id?.let { onPlayerClicked.invoke(it) }
        },
        border = BorderStroke(
            1.dp, Brush.horizontalGradient(
                arrayListOf(BlueGradient, YellowGradient, PinkGradient)
            )
        )
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
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 4.dp, bottom = 4.dp)
            )
            Text(
                text = player.position.toString(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 4.dp, bottom = 4.dp)
            )
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                player.team?.abbreviation?.let {
                    TeamLogosObject.getTeamLogo(
                        it
                    )
                }?.let { painterResource(id = it) }
                    ?.let {
                        Image(
                            painter = it, contentDescription = "", modifier = Modifier
                                .size(24.dp)
                                .padding(end = 4.dp)
                        )
                    }
                Text(
                    text = player.team?.name.toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 4.dp)
                        .align(Alignment.CenterVertically)
                )
            }

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBarView(onSearchApplied: (query: String) -> Unit) {
    var query by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .border(
                BorderStroke(
                    1.dp, Brush.horizontalGradient(
                        arrayListOf(BlueGradient, YellowGradient, PinkGradient)
                    )
                )
            )
    ) {
        TextField(
            value = query,
            onValueChange = { query = it },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            label = { Text("Search") },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (query.isNotEmpty()) {
                            query = ""
                            onSearchApplied.invoke(query)
                            keyboardController?.hide()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.Black
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchApplied.invoke(query)
                    keyboardController?.hide()
                },
                onDone = { keyboardController?.hide() }

            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}