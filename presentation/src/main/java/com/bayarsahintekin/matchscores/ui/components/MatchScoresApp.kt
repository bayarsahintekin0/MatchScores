package com.bayarsahintekin.matchscores.ui.components

import android.annotation.SuppressLint
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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

@Composable
fun NavigationGraph(navController: NavHostController) {
    val backPressDispatcher = LocalOnBackPressedDispatcherOwner.current
    NavHost(navController,
        startDestination = BottomNavItem.Games.screen_route,
        modifier = Modifier.padding(bottom = 44.dp)) {
        composable(BottomNavItem.Teams.screen_route) {
            ProvideAppBarTitle { Text("Teams", fontSize = 18.sp) }
            ProvideAppBarBackButton {
                IconButton(
                    onClick = {},
                    content = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "home"
                        )
                    }
                )
            }
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
            ProvideAppBarTitle { Text("Games",fontSize = 18.sp) }
            ProvideAppBarBackButton {
                IconButton(
                    onClick = {},
                    content = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "home"
                        )
                    }
                )
            }
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
            ProvideAppBarTitle { Text("Players",fontSize = 18.sp) }
            ProvideAppBarBackButton {
                IconButton(
                    onClick = {},
                    content = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "home"
                        )
                    }
                )
            }
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
            ProvideAppBarTitle { Text("Team Detail",fontSize = 18.sp) }
            ProvideAppBarBackButton {
                IconButton(
                    onClick = { backPressDispatcher?.onBackPressedDispatcher?.onBackPressed() },
                    content = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "arrowBackIos"
                        )
                    }
                )
            }
            it.arguments?.getInt(NavigationKeys.Arg.TEAM_ID)?.let { it1 -> TeamDetailScreen() }
        }

        composable(BottomNavItem.GameDetail.screen_route,
            arguments = listOf(navArgument(NavigationKeys.Arg.GAME_ID) { type = NavType.IntType })) {
            ProvideAppBarTitle { Text("Game Detail",fontSize = 18.sp) }
            ProvideAppBarBackButton {
                IconButton(
                    onClick = { backPressDispatcher?.onBackPressedDispatcher?.onBackPressed() },
                    content = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "arrowBackIos"
                        )
                    }
                )
            }
            it.arguments?.getInt(NavigationKeys.Arg.GAME_ID)?.let { it1 -> GameDetailScreen(gameId = it1) }
        }

        composable(BottomNavItem.PlayerDetail.screen_route,
            arguments = listOf(navArgument(NavigationKeys.Arg.PLAYER_ID) { type = NavType.IntType })) {
            ProvideAppBarTitle { Text("Player Detail",fontSize = 18.sp) }
            ProvideAppBarBackButton {
                IconButton(
                    onClick = { backPressDispatcher?.onBackPressedDispatcher?.onBackPressed() },
                    content = {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "arrowBackIos"
                        )
                    }
                )
            }
            PlayerDetailScreen()
        }

    }
}

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
    object Teams: BottomNavItem("Teams", R.drawable.teams,"teams")
    object TeamDetail: BottomNavItem("TeamDetail", R.drawable.ic_team,"teams/{${NavigationKeys.Arg.TEAM_ID}}")
    object Games: BottomNavItem("Games", R.drawable.ic_games,"games")
    object GameDetail: BottomNavItem("GameDetail", R.drawable.ic_games,"games/{${NavigationKeys.Arg.GAME_ID}}")
    object Players: BottomNavItem("Players", R.drawable.ic_player,"players")
    object PlayerDetail: BottomNavItem("PlayerDetail", R.drawable.ic_player,"players/{${NavigationKeys.Arg.PLAYER_ID}}")
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
    BottomNavigation(backgroundColor = Color.White) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 9.sp) },
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