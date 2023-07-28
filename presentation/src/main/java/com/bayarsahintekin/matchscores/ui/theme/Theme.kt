package com.bayarsahintekin.matchscores.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val msDarkColorScheme = darkColorScheme(
    primary = msDarkPrimary,
    onPrimary = msDarkOnPrimary,
    primaryContainer = msDarkPrimaryContainer,
    onPrimaryContainer = msDarkOnPrimaryContainer,
    inversePrimary = msDarkPrimaryInverse,
    secondary = msDarkSecondary,
    onSecondary = msDarkOnSecondary,
    secondaryContainer = msDarkSecondaryContainer,
    onSecondaryContainer = msDarkOnSecondaryContainer,
    tertiary = msDarkTertiary,
    onTertiary = msDarkOnTertiary,
    tertiaryContainer = msDarkTertiaryContainer,
    onTertiaryContainer = msDarkOnTertiaryContainer,
    error = msDarkError,
    onError = msDarkOnError,
    errorContainer = msDarkErrorContainer,
    onErrorContainer = msDarkOnErrorContainer,
    background = msDarkBackground,
    onBackground = msDarkOnBackground,
    surface = msDarkSurface,
    onSurface = msDarkOnSurface,
    inverseSurface = msDarkInverseSurface,
    inverseOnSurface = msDarkInverseOnSurface,
    surfaceVariant = msDarkSurfaceVariant,
    onSurfaceVariant = msDarkOnSurfaceVariant,
    outline = msDarkOutline
)

private val msLightColorScheme = lightColorScheme(
    primary = msLightPrimary,
    onPrimary = msLightOnPrimary,
    primaryContainer = msLightPrimaryContainer,
    onPrimaryContainer = msLightOnPrimaryContainer,
    inversePrimary = msLightPrimaryInverse,
    secondary = msLightSecondary,
    onSecondary = msLightOnSecondary,
    secondaryContainer = msLightSecondaryContainer,
    onSecondaryContainer = msLightOnSecondaryContainer,
    tertiary = msLightTertiary,
    onTertiary = msLightOnTertiary,
    tertiaryContainer = msLightTertiaryContainer,
    onTertiaryContainer = msLightOnTertiaryContainer,
    error = msLightError,
    onError = msLightOnError,
    errorContainer = msLightErrorContainer,
    onErrorContainer = msLightOnErrorContainer,
    background = msLightBackground,
    onBackground = msLightOnBackground,
    surface = msLightSurface,
    onSurface = msLightOnSurface,
    inverseSurface = msLightInverseSurface,
    inverseOnSurface = msLightInverseOnSurface,
    surfaceVariant = msLightSurfaceVariant,
    onSurfaceVariant = msLightOnSurfaceVariant,
    outline = msLightOutline
)

@Composable
fun MSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val msColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> msDarkColorScheme
        else -> msLightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = msColorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    androidx.compose.material3.MaterialTheme(
        colorScheme = msColorScheme,
        typography = msTypography,
        shapes = shapes,
        content = content
    )
}


