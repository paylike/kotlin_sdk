package com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette =
    darkColors(
        primary = PaylikeGreen,
        primaryVariant = PaylikeGreen,
        secondary = PaylikeGreen,
        secondaryVariant = PaylikeGreen,
        surface = PaylikeBlack,
        background = PaylikeBlack,
        error = PaylikeErrorRed,
    )

private val LightColorPalette =
    lightColors(
        primary = PaylikeGreen,
        primaryVariant = PaylikeGreen,
        secondary = PaylikeGreen,
        secondaryVariant = PaylikeGreen,
        surface = PaylikeWhite,
        background = PaylikeWhite,
        error = PaylikeErrorRed,
    )

@Composable
fun PaylikeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors =
        if (darkTheme) {
            DarkColorPalette
        } else {
            LightColorPalette
        }
    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}
