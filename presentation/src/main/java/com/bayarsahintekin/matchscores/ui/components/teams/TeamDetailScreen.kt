package com.bayarsahintekin.matchscores.ui.components.teams

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayarsahintekin.domain.entity.TeamEntity
import com.bayarsahintekin.matchscores.ui.viewmodel.TeamDetailViewModel
import com.bayarsahintekin.matchscores.ui.viewmodel.TeamsViewModel

@SuppressLint("VisibleForTests")
@Composable
fun TeamDetailScreen(teamDetailViewModel: TeamDetailViewModel = hiltViewModel(), teamId: Int){

    Column() {
        Text(text = "Bayaaaaaaar")

        teamDetailViewModel.onInitialState(teamId.toString())
        /*Image(
            painter = painterResource(id = TeamLogosObject.getTeamLogo(teamItem.abbreviation)),
            contentDescription = teamItem.name,
            modifier = Modifier.size(240.dp).align(alignment = Alignment.CenterHorizontally)
        )*/
    }

}