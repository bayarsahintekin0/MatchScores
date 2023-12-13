package com.bayarsahintekin.matchscores.ui.components

import android.annotation.SuppressLint
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.components.base.MSTopAppBar
import com.bayarsahintekin.matchscores.ui.components.base.ProvideAppBarBackButton
import com.bayarsahintekin.matchscores.ui.components.base.ProvideAppBarTitle
import com.bayarsahintekin.matchscores.ui.components.games.GameDetailScreen
import com.bayarsahintekin.matchscores.ui.components.games.GamesScreen
import com.bayarsahintekin.matchscores.ui.components.players.PlayerDetailScreen
import com.bayarsahintekin.matchscores.ui.components.players.PlayersScreen
import com.bayarsahintekin.matchscores.ui.components.teams.TeamDetailScreen
import com.bayarsahintekin.matchscores.ui.components.teams.TeamsScreen
import com.bayarsahintekin.matchscores.ui.theme.msOrange
import com.bayarsahintekin.matchscores.ui.theme.msPurple
import com.bayarsahintekin.matchscores.ui.theme.msTypography
import com.bayarsahintekin.matchscores.ui.theme.zillaSlabFontFamily

@Composable
fun NavigationGraph(navController: NavHostController) {
    val backPressDispatcher = LocalOnBackPressedDispatcherOwner.current
    NavHost(navController,
        startDestination = BottomNavItem.Games.screen_route,
        modifier = Modifier.padding(bottom = 44.dp)) {
        composable(BottomNavItem.Teams.screen_route) {
            ProvideAppBarTitle("Teams")
            ProvideAppBarBackButton (
                icon = R.drawable.ic_basketball_team,
                onClick = {}
            )
            TeamsScreen(
                onTeamClicked = {
                    navController.navigate(BottomNavItem.TeamDetail.screen_route.replace(
                        oldValue = "{${NavigationKeys.Arg.TEAM_ID}}",
                        newValue = it.toString()
                    ))
                }
            )
        }
        composable(BottomNavItem.Games.screen_route) {
            ProvideAppBarTitle ("Games")
            ProvideAppBarBackButton (
                icon = R.drawable.ic_basketball_game,
                onClick = {}
            )
            GamesScreen(
                onGameClicked = {
                    navController.navigate(BottomNavItem.GameDetail.screen_route.replace(
                        oldValue = "{${NavigationKeys.Arg.GAME_ID}}",
                        newValue = it.toString()
                    ))
                }
            )
        }
        composable(BottomNavItem.Players.screen_route) {
            ProvideAppBarTitle ("Players")
            ProvideAppBarBackButton (
                icon = R.drawable.ic_basketball_player,
                onClick = {}
            )
            PlayersScreen(
                onPlayerClicked = {
                    navController.navigate(BottomNavItem.PlayerDetail.screen_route.replace(
                        oldValue = "{${NavigationKeys.Arg.PLAYER_ID}}",
                        newValue = it.toString()
                    ))
                }
            )
        }
        composable(BottomNavItem.TeamDetail.screen_route,
            arguments = listOf(navArgument(NavigationKeys.Arg.TEAM_ID) { type = NavType.IntType })) {
            ProvideAppBarTitle ("Team Detail")
            ProvideAppBarBackButton (
                icon = R.drawable.ic_back,
                onClick = { backPressDispatcher?.onBackPressedDispatcher?.onBackPressed() }
            )
            it.arguments?.getInt(NavigationKeys.Arg.TEAM_ID)?.let { it1 -> TeamDetailScreen() }
        }

        composable(BottomNavItem.GameDetail.screen_route,
            arguments = listOf(navArgument(NavigationKeys.Arg.GAME_ID) { type = NavType.IntType })) {
            ProvideAppBarTitle ("Game Detail")
            ProvideAppBarBackButton (
                icon = R.drawable.ic_back,
                onClick = { backPressDispatcher?.onBackPressedDispatcher?.onBackPressed() }
            )
            it.arguments?.getInt(NavigationKeys.Arg.GAME_ID)?.let { it1 -> GameDetailScreen() }
        }

        composable(BottomNavItem.PlayerDetail.screen_route,
            arguments = listOf(navArgument(NavigationKeys.Arg.PLAYER_ID) { type = NavType.IntType })) {
            ProvideAppBarTitle ("Player Detail")
            ProvideAppBarBackButton (
                icon = R.drawable.ic_back,
                onClick = { backPressDispatcher?.onBackPressedDispatcher?.onBackPressed() }
            )
            PlayerDetailScreen()
        }

    }
}

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
    object Teams: BottomNavItem("Teams", R.drawable.ic_basketball_team,"teams")
    object TeamDetail: BottomNavItem("TeamDetail", R.drawable.ic_basketball_team,"teams/{${NavigationKeys.Arg.TEAM_ID}}")
    object Games: BottomNavItem("Games", R.drawable.ic_basketball_game,"games")
    object GameDetail: BottomNavItem("GameDetail", R.drawable.ic_basketball_game,"games/{${NavigationKeys.Arg.GAME_ID}}")
    object Players: BottomNavItem("Players", R.drawable.ic_basketball_player,"players")
    object PlayerDetail: BottomNavItem("PlayerDetail", R.drawable.ic_basketball_player,"players/{${NavigationKeys.Arg.PLAYER_ID}}")
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView(){
    val navController = rememberNavController()
    Column {
        MSTopAppBar(
            navController
        )

        Scaffold(
            bottomBar = { AppBottomNavigation(navController = navController ) }
        ) {
            NavigationGraph(navController = navController)
        }
    }
}


@Composable
fun AppBottomNavigation(
    navController: NavHostController
) {
    val items = listOf(
        BottomNavItem.Teams,
        BottomNavItem.Games,
        BottomNavItem.Players,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Column{
        Spacer(modifier = Modifier
            .background(msPurple)
            .height(3.dp)
            .fillMaxWidth())
        BottomNavigation(backgroundColor = msOrange) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = item.icon), contentDescription = item.title) },
                    label = { Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = item.title,
                        fontFamily = zillaSlabFontFamily,
                        fontSize = 14.sp) },
                    selectedContentColor = Color.Black,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.screen_route,
                    onClick = {
                        navController.navigate(item.screen_route) {

                            navController.graph.startDestinationRoute?.let { screen_route ->
                                popUpTo(screen_route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }


}
object NavigationKeys {

    object Arg {
        const val PLAYER_ID = "playerId"
        const val GAME_ID = "gameId"
        const val STAT_ID = "statId"
        const val TEAM_ID = "teamId"
    }

    object Route {

    }

}