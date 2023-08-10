package com.bayarsahintekin.matchscores.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.components.teams.TeamsHomeScreen
import com.bayarsahintekin.matchscores.ui.viewmodel.PlayersViewModel
import com.bayarsahintekin.matchscores.ui.viewmodel.TeamsViewModel

@Composable
fun PlayersScreen(playersViewModel: PlayersViewModel = hiltViewModel()){

    val playersUiState = playersViewModel.uiState.collectAsState()

    playersUiState.value.data?.let {
        val a = it
    }

}