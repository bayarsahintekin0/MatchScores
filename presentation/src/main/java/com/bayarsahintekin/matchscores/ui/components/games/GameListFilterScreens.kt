package com.bayarsahintekin.matchscores.ui.components.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
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
import com.bayarsahintekin.matchscores.ui.theme.msLightBackground
import com.bayarsahintekin.matchscores.ui.theme.msLightOnBackground
import com.bayarsahintekin.matchscores.util.TeamLogosObject

@Composable
fun TabScreen(
    items: LazyPagingItems<TeamEntity>,
    onFilterApplied: (GameFilerData) -> Unit,
) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Teams", "Seasons")
    val selectedSeason = remember { mutableStateOf<Int?>(null) }
    val selectedTeam = remember { mutableStateOf<Int?>(null) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {
                onFilterApplied.invoke(GameFilerData(
                    selectedTeamId = selectedTeam.value,
                    selectedSeason = selectedSeason.value
                ))

            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = msLightBackground,
                contentColor = msLightOnBackground
            ), modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 4.dp, top = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = stringResource(id = R.string.apply_filter))
        }
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
            0 -> {
                val teams = arrayListOf<Int>()
                for (i in items.itemSnapshotList.items) {
                    teams.add(i.id)
                }

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
                            ) { index ->
                                val team = items[index]
                                val isChecked = teams[index] == selectedTeam.value
                                if (team != null) {
                                    Card(
                                        shape = RoundedCornerShape(8.dp),
                                        elevation = 4.dp,
                                        modifier = Modifier
                                            .padding(2.dp)
                                    ) {
                                        Row(modifier = Modifier.padding(vertical = 2.dp)) {
                                            Checkbox(checked = isChecked, onCheckedChange = {
                                                if (items[index]?.id == selectedTeam.value)
                                                    selectedTeam.value = null
                                                else
                                                    items[index]?.id?.let {id ->
                                                        selectedTeam.value = id
                                                    }
                                            })

                                            Image(
                                                painter = painterResource(id = TeamLogosObject.getTeamLogo(team.abbreviation)),
                                                contentDescription = team.name,
                                                modifier = Modifier
                                                    .padding(start = 8.dp, end = 4.dp)
                                                    .align(Alignment.CenterVertically)
                                                    .size(24.dp)
                                            )
                                            Text(
                                                text = team.fullName,
                                                modifier = Modifier.align(Alignment.CenterVertically)
                                            )
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
            1 -> {
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
                            Card(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.padding(4.dp)
                            ) {
                                Row {
                                    Checkbox(checked = years[index] == selectedSeason.value, onCheckedChange = {
                                        if (years[index] == selectedSeason.value)
                                            selectedSeason.value = null
                                        else
                                            selectedSeason.value = years[index]

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
                }
            }
        }
    }
}

data class GameFilerData(val selectedTeamId:Int? = null, val selectedSeason: Int? = null)


