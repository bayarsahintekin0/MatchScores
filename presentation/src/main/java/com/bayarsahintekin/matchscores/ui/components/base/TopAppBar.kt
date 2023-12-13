package com.bayarsahintekin.matchscores.ui.components.base


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.FloatingWindow
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.LocalOwnersProvider
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.theme.msOrange
import com.bayarsahintekin.matchscores.ui.theme.msPurple
import com.bayarsahintekin.matchscores.ui.theme.zillaSlabFontFamily
import com.bayarsahintekin.matchscores.ui.viewmodel.TopAppBarViewModel
import kotlinx.coroutines.flow.filterNot


@Composable
fun MSTopAppBar(navController: NavController) {
    val currentContentBackStackEntry by produceState(
        initialValue = null as NavBackStackEntry?,
        producer = {
            navController.currentBackStackEntryFlow
                .filterNot { it.destination is FloatingWindow }
                .collect{ value = it }
        }
    )
    Column {
        TopAppBar(
            navigationIcon = {
                AppBarBackButton(navBackStackEntry = currentContentBackStackEntry)
            },
            backgroundColor = msOrange,
            title = {
                AppBarTitle(currentContentBackStackEntry)
            },
            actions = {
                AppBarAction(currentContentBackStackEntry)
            }
        )
        Spacer(modifier = Modifier
            .background(msPurple)
            .height(3.dp)
            .fillMaxWidth())
    }
}

/**
 * For add view to appbar.
 */
@Composable
fun ProvideAppBarAction(actions: @Composable RowScope.() -> Unit) {
    if (LocalViewModelStoreOwner.current == null || LocalViewModelStoreOwner.current !is NavBackStackEntry)
        return
    val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
    SideEffect {
        actionViewModel.actionState = actions
    }
}

@Composable
fun ProvideAppBarTitle(title: String) {
    if (LocalViewModelStoreOwner.current == null || LocalViewModelStoreOwner.current !is NavBackStackEntry)
        return
    val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
    SideEffect {
        actionViewModel.titleState = {
            Text(text = title,
                fontSize = 20.sp,
                fontFamily = zillaSlabFontFamily)
        }
    }
}

@Composable
fun ProvideAppBarBackButton(icon: Int, onClick :() -> Unit?) {
    if (LocalViewModelStoreOwner.current == null || LocalViewModelStoreOwner.current !is NavBackStackEntry)
        return
    val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
    SideEffect {
        actionViewModel.backButtonState = {
            IconButton(
                onClick = { onClick.invoke() },
                content = {
                    Icon(
                        painterResource(id = icon),
                        contentDescription = "appbar",
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    }
}

@Composable
fun RowScope.AppBarAction(navBackStackEntry: NavBackStackEntry?) {
    val stateHolder = rememberSaveableStateHolder()
    navBackStackEntry?.LocalOwnersProvider(stateHolder) {
        val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
        actionViewModel.actionState?.let { it() }
    }
}


@Composable
fun AppBarTitle(navBackStackEntry: NavBackStackEntry?) {
    val stateHolder = rememberSaveableStateHolder()
    navBackStackEntry?.LocalOwnersProvider(stateHolder) {
        val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
        actionViewModel.titleState?.let {
            it()
        }
    }
}

@Composable
fun AppBarBackButton(navBackStackEntry: NavBackStackEntry?) {
    val stateHolder = rememberSaveableStateHolder()
    navBackStackEntry?.LocalOwnersProvider(stateHolder) {
        val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
        actionViewModel.backButtonState?.let {
            it()
        }
    }
}




