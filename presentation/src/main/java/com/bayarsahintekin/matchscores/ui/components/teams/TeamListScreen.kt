package com.bayarsahintekin.matchscores.ui.components.teams

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.bayarsahintekin.domain.entity.players.PlayerEntity
import com.bayarsahintekin.domain.entity.teams.TeamListEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.matchscores.ui.components.players.PlayerItem
import com.bayarsahintekin.matchscores.ui.theme.BlueGradient
import com.bayarsahintekin.matchscores.ui.theme.G1
import com.bayarsahintekin.matchscores.ui.theme.G2
import com.bayarsahintekin.matchscores.ui.theme.PinkGradient
import com.bayarsahintekin.matchscores.ui.theme.YellowGradient
import com.bayarsahintekin.matchscores.ui.theme.msOrange
import com.bayarsahintekin.matchscores.ui.theme.msPurple
import com.bayarsahintekin.matchscores.ui.theme.zillaSlabFontFamily
import com.bayarsahintekin.matchscores.ui.viewmodel.TeamsViewModel
import com.bayarsahintekin.matchscores.util.TeamLogosObject

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TeamsScreen(teamsViewModel: TeamsViewModel = hiltViewModel(), onTeamClicked: (teamId: Int) -> Unit) {

    val items = teamsViewModel.teams.collectAsLazyPagingItems()
    TeamsHomeScreen(items = items,onTeamClicked)

}

@ExperimentalFoundationApi
@Composable
fun TeamsHomeScreen(items: LazyPagingItems<TeamEntity>, onTeamClicked: (teamId: Int) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (items.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(count = items.itemCount,
                    key = items.itemKey { it.id }
                ) {
                    val team = items[it]
                    if (team != null) {
                        TeamItem(item = team,onTeamClicked)
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

@OptIn(ExperimentalMaterialApi::class, ExperimentalTextApi::class)
@Composable
fun TeamItem(item: TeamEntity, onTeamClicked: (teamId: Int) -> Unit) {
    Card(modifier = Modifier.padding(4.dp),
        onClick = {
           onTeamClicked.invoke(item.id)
        },
        border = BorderStroke(1.dp,Brush.horizontalGradient(
        arrayListOf(msOrange, msPurple, msOrange)
    ))) {
        Column(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = TeamLogosObject.getTeamLogo(item.abbreviation)),
                contentDescription = item.name,
                modifier = Modifier
                    .height(80.dp)
                    .padding(top = 16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Text(
                text = item.name,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontFamily = zillaSlabFontFamily,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = arrayListOf(
                            msOrange,
                            msPurple,
                            msOrange),
                        tileMode = TileMode.Mirror
                    ),
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = 8.dp, bottom = 8.dp)
            )
        }
    }
}
