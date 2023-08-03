package com.bayarsahintekin.matchscores.ui.components.teams

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayarsahintekin.domain.entity.TeamEntity
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.components.base.MSTopAppBar
import com.bayarsahintekin.matchscores.ui.theme.msTypography
import com.bayarsahintekin.matchscores.ui.viewmodel.TeamDetailViewModel
import com.bayarsahintekin.matchscores.ui.viewmodel.TeamsViewModel
import com.bayarsahintekin.matchscores.util.TeamLogosObject

@SuppressLint("VisibleForTests")
@Composable
fun TeamDetailScreen(
    teamDetailViewModel: TeamDetailViewModel = hiltViewModel(),
    teamId: Int
) {
    val teamUiState = teamDetailViewModel.uiState.collectAsState()
    teamDetailViewModel.onInitialState(teamId.toString())
    teamUiState.value.data?.let {
        TeamDetailScreen(team = it)
    }
}

@Composable
fun TeamDetailScreen(team: TeamEntity) {
    Column(
        Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = TeamLogosObject.getTeamLogo(team.abbreviation)),
            contentDescription = team.name,
            Modifier.size(240.dp)
        )
        Column (Modifier.padding(top = 32.dp)){
            Text(
                text = stringResource(
                    id = R.string.short_name
                ) + ": " + team.name,
                style = msTypography.titleLarge
            )
            Text(
                text = stringResource(id = R.string.full_name) + ": " + team.fullName,
                style = msTypography.titleLarge
            )
            Text(
                text = stringResource(id = R.string.conference) + ": " + team.conference,
                style = msTypography.titleLarge
            )
            Text(
                text = stringResource(id = R.string.division) + ": " + team.division,
                style = msTypography.titleLarge
            )
            Text(
                text = stringResource(id = R.string.city) + ": " + team.city,
                style = msTypography.titleLarge
            )
        }
    }
}