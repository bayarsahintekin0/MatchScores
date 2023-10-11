package com.bayarsahintekin.matchscores.ui.components.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bayarsahintekin.matchscores.R
import com.bayarsahintekin.matchscores.ui.theme.BlueGradient
import com.bayarsahintekin.matchscores.ui.theme.PinkGradient
import com.bayarsahintekin.matchscores.ui.theme.YellowGradient

@Composable
fun MSText(title: String, value: String){
    Box(
        Modifier
            .padding(2.dp)
            .border(
                BorderStroke(
                    1.dp,
                    Brush.horizontalGradient(
                        arrayListOf(
                            BlueGradient,
                            YellowGradient,
                            PinkGradient
                        )
                    )
                )
            )) {
        Text(text = "$title : $value",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth())
    }
}