package com.bayarsahintekin.matchscores.ui.components.players

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bayarsahintekin.domain.entity.stats.StatsEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.domain.utils.onSuccess
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.components.base.MSText
import com.bayarsahintekin.matchscores.ui.theme.BlueGradient
import com.bayarsahintekin.matchscores.ui.theme.PinkGradient
import com.bayarsahintekin.matchscores.ui.theme.YellowGradient
import com.bayarsahintekin.matchscores.ui.theme.msOrange
import com.bayarsahintekin.matchscores.ui.theme.msPurple
import com.bayarsahintekin.matchscores.ui.viewmodel.PlayerDetailViewModel
import com.bayarsahintekin.matchscores.util.TeamLogosObject
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@Composable
fun PlayerDetailScreen(viewModel: PlayerDetailViewModel = hiltViewModel()) {

    val stats = viewModel.getStats().collectAsLazyPagingItems()

    val player = viewModel.uiState.collectAsState()
    player.value.let {
        MainView(it, stats, viewModel)
    }
}

@Composable
fun PlayerDetailView(
    player: PlayerDetailViewModel.PlayerDetailsUiState,
    stats: LazyPagingItems<StatsEntity>,
    viewModel: PlayerDetailViewModel,
    onStatSelected: (stat: StatsEntity) -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_player_holder),
            contentDescription = player.firstName,
            modifier = Modifier
                .height(120.dp)
                .padding(top = 8.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${player.firstName} ${player.lastName}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = player.position.toString(),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            Modifier
                .padding(8.dp)
                .border(
                    BorderStroke(
                        1.dp,
                        Brush.horizontalGradient(
                            arrayListOf(
                                msOrange,
                                msPurple,
                                msOrange
                            )
                        )
                    )
                )
        ) {
            Text(
                text = stringResource(id = R.string.team) + " : " + player.teamFullName,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }

        Box(
            Modifier
                .padding(8.dp)
                .border(
                    BorderStroke(
                        1.dp,
                        Brush.horizontalGradient(
                            arrayListOf(
                                msOrange,
                                msPurple,
                                msOrange
                            )
                        )
                    )
                )
        ) {
            var height = stringResource(id = R.string.unknown)
            if (player.heightInches != null)
                height = "${player.heightInches} ft"
            Text(
                text = stringResource(id = R.string.height) + " : " + height,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }

        Box(
            Modifier
                .padding(8.dp)
                .border(
                    BorderStroke(
                        1.dp,
                        Brush.horizontalGradient(
                            arrayListOf(
                                BlueGradient,
                                YellowGradient,
                                PinkGradient
                            )
                        )
                    )
                )
        ) {
            var weight = stringResource(id = R.string.unknown)
            if (player.weightPounds != null)
                weight = "${player.weightPounds} lbs"

            Text(
                text = (stringResource(id = R.string.weight) + " : " + weight),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }

        Box(
            Modifier
                .padding(8.dp)
                .border(
                    BorderStroke(
                        1.dp,
                        Brush.horizontalGradient(
                            arrayListOf(
                                msOrange,
                                msPurple,
                                msOrange
                            )
                        )
                    )
                )
        ) {
            Text(
                text = (stringResource(id = R.string.player_all_stats)),
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            if (stats.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyRow(
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(
                        count = stats.itemCount,

                        ) {
                        val stat = stats[it]
                        val homeTeamResult = remember { mutableStateOf<TeamEntity?>(null) }
                        val awayTeamResult = remember { mutableStateOf<TeamEntity?>(null) }

                        LaunchedEffect(Unit) {
                            val homeData =
                                viewModel.getTeamById(stat?.game?.home_team_id.toString())
                            val awayData =
                                viewModel.getTeamById(stat?.game?.visitor_team_id.toString())
                            homeData.onSuccess { team -> homeTeamResult.value = team }
                            awayData.onSuccess { team -> awayTeamResult.value = team }
                        }

                        if (stat != null && stat.game.date.isNotEmpty() && stat.game.date != " ") {
                            PlayerStatView(
                                stat = stat,
                                homeTeamResult,
                                awayTeamResult,
                                onStatSelected
                            )
                        }
                    }

                    item {
                        if (stats.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerStatView(
    stat: StatsEntity,
    homeTeamResult: MutableState<TeamEntity?>,
    awayTeamResult: MutableState<TeamEntity?>,
    onStatSelected: (stat: StatsEntity) -> Unit
) {
    Card(
        modifier = Modifier.padding(4.dp),
        onClick = { onStatSelected.invoke(stat) },
        border = BorderStroke(
            1.dp, Brush.horizontalGradient(
                arrayListOf(msOrange,
                    msPurple,
                    msOrange)
            )
        )
    ) {
        Row {
            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(
                    text = formatDate(stat.game.date).toString(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 4.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
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
                        .padding(top = 4.dp, bottom = 4.dp)
                )
            }

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Row(Modifier.padding(horizontal = 8.dp)) {
                    awayTeamResult.value?.abbreviation?.let {
                        TeamLogosObject.getTeamLogo(
                            it
                        )
                    }?.let { painterResource(id = it) }
                        ?.let {
                            Image(
                                painter = it, contentDescription = "",
                                Modifier
                                    .size(18.dp)
                                    .padding(end = 4.dp)
                            )
                        }

                    awayTeamResult.value?.name?.let { Text(text = it) }
                }
                Text(
                    text = " @ ",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Row(Modifier.padding(horizontal = 8.dp)) {
                    homeTeamResult.value?.abbreviation?.let {
                        TeamLogosObject.getTeamLogo(
                            it
                        )
                    }?.let { painterResource(id = it) }
                        ?.let { Image(painter = it, contentDescription = "", Modifier.size(18.dp)) }
                    homeTeamResult.value?.name?.let { Text(text = it) }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainView(
    player: PlayerDetailViewModel.PlayerDetailsUiState,
    stats: LazyPagingItems<StatsEntity>,
    viewModel: PlayerDetailViewModel
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    val selectedStat = remember {
        mutableStateOf<StatsEntity?>(null)
    }


    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            selectedStat.value?.let { StatDetailModalView(stat = it, viewModel = viewModel) }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        PlayerDetailView(player = player, stats = stats, viewModel = viewModel, onStatSelected = {
            selectedStat.value = it
            coroutineScope.launch {
                if (sheetState.isVisible) sheetState.hide()
                else sheetState.show()
            }
        })
    }
}

@Composable
fun StatDetailModalView(stat: StatsEntity, viewModel: PlayerDetailViewModel) {
    val homeTeamResult = remember { mutableStateOf<TeamEntity?>(null) }
    val awayTeamResult = remember { mutableStateOf<TeamEntity?>(null) }

    LaunchedEffect(Unit) {
        val homeData = viewModel.getTeamById(stat?.game?.home_team_id.toString())
        val awayData = viewModel.getTeamById(stat?.game?.visitor_team_id.toString())
        homeData.onSuccess { team -> homeTeamResult.value = team }
        awayData.onSuccess { team -> awayTeamResult.value = team }
    }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        stat.apply {
            Text(
                text = player.firstName + " " + player.lastName,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )
            Row(Modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally)) {
                awayTeamResult.value?.abbreviation?.let {
                    TeamLogosObject.getTeamLogo(
                        it
                    )
                }?.let { painterResource(id = it) }
                    ?.let {
                        Image(
                            painter = it, contentDescription = "",
                            Modifier
                                .size(24.dp)
                                .padding(end = 4.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }

                awayTeamResult.value?.name?.let { Text(text = it, fontSize = 20.sp, fontWeight = FontWeight.Bold) }

                Text(text = " @ ", fontWeight = FontWeight.Bold)

                homeTeamResult.value?.name?.let { Text(text = it, fontSize = 20.sp, fontWeight = FontWeight.Bold) }

                homeTeamResult.value?.abbreviation?.let {
                    TeamLogosObject.getTeamLogo(
                        it
                    )
                }?.let { painterResource(id = it) }
                    ?.let { Image(painter = it, contentDescription = "", Modifier
                        .size(18.dp)
                        .align(Alignment.CenterVertically)
                    ) }
            }

            MSText(title = stringResource(id = R.string.asists), value = stat.ast.toString())
            MSText(title = stringResource(id = R.string.block), value = stat.blk.toString())
            MSText(title = stringResource(id = R.string.points), value = stat.pts.toString())
            MSText(title = stringResource(id = R.string.rebounds), value = stat.reb.toString())
            MSText(title = stringResource(id = R.string.steal), value = stat.stl.toString())
            MSText(
                title = stringResource(id = R.string.turn_over),
                value = stat.turnover.toString()
            )
            MSText(
                title = stringResource(id = R.string.team),
                value = stat.team.fullName.toString()
            )
            MSText(
                title = stringResource(id = R.string.defensive_rebound),
                value = stat.dreb.toString()
            )
            MSText(
                title = stringResource(id = R.string.three_point_percentage),
                value = stat.fg3_pct.toString()
            )
            MSText(
                title = stringResource(id = R.string.three_point_attempt),
                value = stat.ast.toString()
            )
            MSText(
                title = stringResource(id = R.string.three_point_made),
                value = stat.ast.toString()
            )
            MSText(
                title = stringResource(id = R.string.field_goal_percentage),
                value = stat.fg_pct.toString()
            )
            MSText(
                title = stringResource(id = R.string.field_goal_attempt),
                value = stat.fga.toString()
            )
            MSText(
                title = stringResource(id = R.string.field_goal_made),
                value = stat.fgm.toString()
            )
            MSText(
                title = stringResource(id = R.string.free_throw_percentage),
                value = stat.ft_pct.toString()
            )
            MSText(
                title = stringResource(id = R.string.free_throw_attempt),
                value = stat.fta.toString()
            )
            MSText(
                title = stringResource(id = R.string.free_throw_made),
                value = stat.ftm.toString()
            )
            MSText(title = stringResource(id = R.string.minutes), value = stat.min.toString())
            MSText(
                title = stringResource(id = R.string.offensive_rebound),
                value = stat.oreb.toString()
            )
            MSText(title = stringResource(id = R.string.personal_foul), value = stat.pf.toString())
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun formatDate(dateString: String): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val outputFormat = SimpleDateFormat("d MMM yyyy")

    val date = inputFormat.parse(dateString)
    return date?.let { outputFormat.format(it) }
}
