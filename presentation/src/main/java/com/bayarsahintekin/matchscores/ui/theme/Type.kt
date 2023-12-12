package com.bayarsahintekin.matchscores.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bayarsahintekin.matchscores.R

val zillaSlabFontFamily = FontFamily(
    Font(R.font.zilla_slab_semi_bold,FontWeight.SemiBold),
    Font(R.font.zilla_slab_semi_bold_italic,FontWeight.SemiBold,FontStyle.Italic),
    Font(R.font.zilla_slab_bold,FontWeight.Bold),
    Font(R.font.zilla_slab_bold_italic,FontWeight.Bold, FontStyle.Italic),
    Font(R.font.zilla_slab_light,FontWeight.Light),
    Font(R.font.zilla_slab_light_italic,FontWeight.Light, FontStyle.Italic),
    Font(R.font.zilla_slab_medium,FontWeight.Medium),
    Font(R.font.zilla_slab_medium_italic,FontWeight.Medium, FontStyle.Italic),
    Font(R.font.zilla_slab_regular,FontWeight.Normal),
    Font(R.font.zilla_slab_italic,FontWeight.Normal ,FontStyle.Italic),
)
val msTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = zillaSlabFontFamily,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)
