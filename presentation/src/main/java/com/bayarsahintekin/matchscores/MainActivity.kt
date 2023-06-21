package com.bayarsahintekin.matchscores

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.bayarsahintekin.matchscores.ui.components.GamesScreen
import com.bayarsahintekin.matchscores.ui.components.PlayersScreen
import com.bayarsahintekin.matchscores.ui.components.StatsScreen
import com.bayarsahintekin.matchscores.ui.components.teams.TeamDetailScreen
import com.bayarsahintekin.matchscores.ui.components.teams.TeamsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    MainScreenView()
                }

        }

        //observeViewModel()

    }


    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(navController,
            startDestination = BottomNavItem.Teams.screen_route,
            modifier = Modifier.padding(bottom = 44.dp)) {
            composable(BottomNavItem.Stats.screen_route) {
                StatsScreen()
            }
            composable(BottomNavItem.Teams.screen_route) {
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
                GamesScreen()
            }
            composable(BottomNavItem.Players.screen_route) {
                PlayersScreen()
            }
            composable(BottomNavItem.TeamDetail.screen_route,
                arguments = listOf(navArgument("teamId") { type = NavType.IntType })) {
                it.arguments?.getInt("teamId")?.let { it1 -> TeamDetailScreen(teamId = it1) }
            }
        }
    }

    sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
        object Stats : BottomNavItem("Stats", R.drawable.ic_stats,"stats")
        object Teams: BottomNavItem("Teams",R.drawable.ic_team,"teams")
        object TeamDetail: BottomNavItem("TeamDetail",R.drawable.ic_team,"teams/{teamId}")
        object Games: BottomNavItem("Games",R.drawable.ic_games,"games")
        object Players: BottomNavItem("Players",R.drawable.ic_player,"players")
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainScreenView(){
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { AppBottomNavigation(navController = navController ) }
        ) {
            NavigationGraph(navController = navController)
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
   /* private fun observeViewModel() = with(viewModel) {
        lifecycleScope.launch {
            uiState?.collect {
                handleMovieDetailsUiState(it)
            }
        }
    }

    private fun handleMovieDetailsUiState(data: ListResponseEntity){
        val a = data
        val ba = data
    }*/
}






@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}
