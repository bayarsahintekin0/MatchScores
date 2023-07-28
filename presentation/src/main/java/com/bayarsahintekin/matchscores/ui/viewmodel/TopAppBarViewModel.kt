package com.bayarsahintekin.matchscores.ui.viewmodel

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.referentialEqualityPolicy
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TopAppBarViewModel : ViewModel() {
    var titleState by mutableStateOf(null as (@Composable () -> Unit)?, referentialEqualityPolicy())
    var actionState by mutableStateOf(null as (@Composable RowScope.() -> Unit)?, referentialEqualityPolicy())
    var backButtonState by mutableStateOf(null as (@Composable () -> Unit)?, referentialEqualityPolicy())
}