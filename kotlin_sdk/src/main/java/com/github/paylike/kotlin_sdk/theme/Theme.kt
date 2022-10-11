package com.github.paylike.kotlin_sdk.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PaylikeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val systemRespectedColorPalette =
        if (darkTheme) {
            PaylikeDarkColorPalette
        } else {
            PaylikeLightColorPalette
        }
    systemUiController.setSystemBarsColor(
        color = systemRespectedColorPalette.primaryVariant,
        darkIcons = !darkTheme,
    )

    CompositionLocalProvider(
        LocalPaylikePaddings provides PaylikePaddings(),
    ) {
        MaterialTheme(
            colors = systemRespectedColorPalette,
            typography = PaylikeTypography,
            shapes = PaylikeShapes,
            content = content,
        )
    }
}

object PaylikeMaterialTheme {
    val colors: Colors
        @Composable @ReadOnlyComposable get() = MaterialTheme.colors

    val typography: Typography
        @Composable @ReadOnlyComposable get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable @ReadOnlyComposable get() = MaterialTheme.shapes

    val paddings: PaylikePaddings
        @Composable @ReadOnlyComposable get() = LocalPaylikePaddings.current
}
