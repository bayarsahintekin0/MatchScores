package com.bayarsahintekin.matchscores.ui.components.base


import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.FloatingWindow
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.LocalOwnersProvider
import com.bayarsahintekin.matchscores.ui.viewmodel.TopAppBarViewModel
import kotlinx.coroutines.flow.filterNot


@OptIn(ExperimentalMaterial3Api::class)
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
    TopAppBar(
        navigationIcon = {
            AppBarBackButton(navBackStackEntry = currentContentBackStackEntry)
        },
        title = {
            AppBarTitle(currentContentBackStackEntry)
        },
        actions = {
            AppBarAction(currentContentBackStackEntry)
        }
    )
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
fun ProvideAppBarTitle(title: @Composable () -> Unit) {
    if (LocalViewModelStoreOwner.current == null || LocalViewModelStoreOwner.current !is NavBackStackEntry)
        return
    val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
    SideEffect {
        actionViewModel.titleState = title
    }
}

@Composable
fun ProvideAppBarBackButton(backButton: @Composable () -> Unit) {
    if (LocalViewModelStoreOwner.current == null || LocalViewModelStoreOwner.current !is NavBackStackEntry)
        return
    val actionViewModel = viewModel(initializer = { TopAppBarViewModel() })
    SideEffect {
        actionViewModel.backButtonState = backButton
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




