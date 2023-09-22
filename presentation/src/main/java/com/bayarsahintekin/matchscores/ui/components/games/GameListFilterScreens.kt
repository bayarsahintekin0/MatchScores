package com.bayarsahintekin.matchscores.ui.components.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.util.TeamLogosObject

@Composable
fun GameListFilterTeamsScreen(items: LazyPagingItems<TeamEntity>,
                              onTeamClicked:(id: Int)-> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (items.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(count = items.itemCount,
                    key = items.itemKey { it.id }
                ) {
                    val team = items[it]
                    if (team != null) {
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            elevation = 4.dp,
                            modifier = Modifier
                                .padding(2.dp)
                                .clickable {
                                    onTeamClicked.invoke(team.id)
                                }
                        ) {
                            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                                Image(
                                    painter = painterResource(id = TeamLogosObject.getTeamLogo(team.abbreviation)),
                                    contentDescription = team.name,
                                    modifier = Modifier
                                        .padding(start = 8.dp, end = 4.dp)
                                        .size(24.dp)
                                )
                                Text(text = team.fullName)
                            }
                        }
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

@Composable
fun GameListFilterSeasonsScreen(onSeasonSelected:(seasons:ArrayList<Int>) -> Unit) {
    val selectedSeasons = arrayListOf<Int>()
    var selectedItems by remember { mutableStateOf(listOf<Int>()) }
    //var isChecked by remember { mutableStateOf(false) }
    val years = arrayListOf<Int>()
    for (i in 2000..2023)
        years.add(i)
    Column {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 120.dp),
            contentPadding = PaddingValues(8.dp),

            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(years.size) { index ->
                val isChecked = selectedItems.contains(years[index])
                Card(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Row {
                        Checkbox(checked = isChecked, onCheckedChange = {
                            if (it)
                                selectedItems = selectedItems + years[index]
                                //selectedSeasons.add(years[index])
                            else
                                selectedItems = selectedItems - years[index]
                                //selectedSeasons.remove(years[index])

                        })
                        Text(
                            text = years[index].toString(), modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }

        Button(onClick = {
            onSeasonSelected.invoke(selectedItems as ArrayList<Int>)
        }, modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 16.dp)) {
            Text(text = stringResource(id = R.string.apply_filter))
        }

    }

}


@Composable
fun TabScreen(items: LazyPagingItems<TeamEntity>,
              onTeamClicked:(id: Int)-> Unit,
              onSeasonSelected:(seasons:ArrayList<Int>) -> Unit) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Teams", "Seasons")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex, backgroundColor = Color.White) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        when (index) {
                            0 -> Icon(
                                painter = painterResource(id = R.drawable.ic_basketball_team),
                                contentDescription = "Teams",
                                modifier = Modifier.size(24.dp)
                            )

                            1 -> Icon(
                                painter = painterResource(id = R.drawable.ic_calender),
                                contentDescription = "Seasons",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                )
            }
        }
        when (tabIndex) {
            0 -> GameListFilterTeamsScreen(items,onTeamClicked)
            1 -> GameListFilterSeasonsScreen(onSeasonSelected)
        }
    }
}


