package com.bayarsahintekin.matchscores.ui.components.games

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayarsahintekin.domain.entity.games.GameEntity
import com.bayarsahintekin.domain.entity.teams.TeamEntity
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.components.base.MSText
import com.bayarsahintekin.matchscores.ui.theme.zillaSlabFontFamily
import com.bayarsahintekin.matchscores.ui.viewmodel.GameDetailViewModel
import com.bayarsahintekin.matchscores.util.TeamLogosObject
import java.text.SimpleDateFormat

@Composable
fun GameDetailScreen(
    gameDetailViewModel: GameDetailViewModel = hiltViewModel()
) {

    val game = gameDetailViewModel.uiState.collectAsState()

    game.value.data?.let { GameDetail(game = it) }


}

@Composable
fun GameDetail(game: GameEntity) {
    Column {
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.align(Alignment.CenterVertically).padding(end = 8.dp)) {
                Image(
                    painter = painterResource(id = TeamLogosObject.getTeamLogo(game.visitorTeam.abbreviation)),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
                Text(text = game.visitorTeam.name,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 8.dp),
                    fontSize = 18.sp,
                    fontFamily = zillaSlabFontFamily,
                    fontWeight = FontWeight.W400)
            }

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = game.status,
                    fontFamily = zillaSlabFontFamily,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Card(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 8.dp, bottom = 8.dp),

                    elevation = 8.dp,
                ) {
                    Text(
                        text = game.visitorTeamScore.toString() + " - " + game.homeTeamScore,
                        fontWeight = FontWeight.Bold,
                        fontFamily = zillaSlabFontFamily,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(4.dp)

                    )
                }
                Text(text = game.date,
                    fontFamily = zillaSlabFontFamily,
                    )
            }

            Row(modifier = Modifier.align(Alignment.CenterVertically).padding(end = 8.dp)) {
                Text(text = game.homeTeam.name,
                    fontFamily = zillaSlabFontFamily,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400)
                Image(
                    painter = painterResource(id = TeamLogosObject.getTeamLogo(game.homeTeam.abbreviation)),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        MSText(title = stringResource(id = R.string.post_season), value = if (game.postseason) "Yes" else "No")
        MSText(title = stringResource(id = R.string.period), value = game.period.toString())
        MSText(title = stringResource(id = R.string.season), value = game.season.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun GameDetailPreview(@PreviewParameter(GameProvider::class) game: GameEntity) {
    GameDetail(game = game)
}

class GameProvider : PreviewParameterProvider<GameEntity> {
    override val values = sequenceOf(
        GameEntity(
            id = 32283,
            date = "2014-01-26 00:00:00 UTC",
            homeTeam = TeamEntity(
                id = 2,
                abbreviation = "BOS",
                city = "Boston",
                conference = "East",
                division = "Atlantic",
                fullName = "Boston Celtics",
                name = "Celtics"
            ),
            homeTeamScore = 79,
            period = 4,
            postseason = false,
            season = 2013,
            status = "Final",
            time = " ",
            visitorTeam =
            TeamEntity(
                id = 3,
                abbreviation = "BKN",
                city = "Brooklyn",
                conference = "East",
                division = "Atlantic",
                fullName = "Brooklyn Nets",
                name = "Nets"
            ),
            visitorTeamScore = 85
        )
    )
}