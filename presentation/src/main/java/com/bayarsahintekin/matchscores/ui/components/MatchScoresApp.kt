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
import com.bayarsahintekin.matchscores.ui.components.games.GamesScreen
import com.bayarsahintekin.matchscores.ui.components.players.PlayersScreen
import com.bayarsahintekin.matchscores.ui.components.stats.StatsScreen
import com.bayarsahintekin.matchscores.ui.components.teams.TeamDetailScreen
import com.bayarsahintekin.matchscores.ui.components.teams.TeamsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    val backPressDispatcher = LocalOnBackPressedDispatcherOwner.current
    NavHost(navController,
        startDestination = BottomNavItem.Stats.screen_route,
        modifier = Modifier.padding(bottom = 44.dp)) {
        composable(BottomNavItem.Stats.screen_route) {
            ProvideAppBarTitle {
                Text("Stats" ,modifier = Modifier, fontSize = 18.sp)
            }
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
            StatsScreen()
        }
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
                        oldValue = "{teamId}",
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
            GamesScreen()
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
            PlayersScreen()
        }
        composable(BottomNavItem.TeamDetail.screen_route,
            arguments = listOf(navArgument("teamId") { type = NavType.IntType })) {
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
            it.arguments?.getInt("teamId")?.let { it1 -> TeamDetailScreen(teamId = it1) }
        }
    }
}

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
    object Stats : BottomNavItem("Stats", R.drawable.stats,"stats")
    object Teams: BottomNavItem("Teams", R.drawable.teams,"teams")
    object TeamDetail: BottomNavItem("TeamDetail", R.drawable.ic_team,"teams/{teamId}")
    object Games: BottomNavItem("Games", R.drawable.ic_games,"games")
    object Players: BottomNavItem("Players", R.drawable.ic_player,"players")
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
        BottomNavItem.Stats,
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