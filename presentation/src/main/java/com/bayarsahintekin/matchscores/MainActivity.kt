package com.bayarsahintekin.matchscores

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.camera.core.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bayarsahintekin.domain.entity.ListResponseEntity
import com.bayarsahintekin.matchscores.ui.components.GamesScreen
import com.bayarsahintekin.matchscores.ui.components.PlayersScreen
import com.bayarsahintekin.matchscores.ui.components.StatsScreen
import com.bayarsahintekin.matchscores.ui.components.TeamsScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        NavHost(navController, startDestination = BottomNavItem.Stats.screen_route) {
            composable(BottomNavItem.Stats.screen_route) {
                StatsScreen()
            }
            composable(BottomNavItem.Teams.screen_route) {
                TeamsScreen()
            }
            composable(BottomNavItem.Games.screen_route) {
                GamesScreen()
            }
            composable(BottomNavItem.Players.screen_route) {
                PlayersScreen()
            }
        }
    }

    sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){
        object Stats : BottomNavItem("Stats", R.drawable.ic_stats,"stats")
        object Teams: BottomNavItem("Teams",R.drawable.ic_team,"teams")
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
