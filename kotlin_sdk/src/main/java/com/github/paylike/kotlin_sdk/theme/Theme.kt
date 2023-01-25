package com.github.paylike.kotlin_sdk.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Basically inherited [@Composable MaterialTheme(...) {}] function and uses it with the overridden
 * Paylike style parameters.
 */
@Composable
fun PaylikeTheme(
    colors: Colors =
        if (isSystemInDarkTheme()) PaylikeDarkColorPalette else PaylikeLightColorPalette,
    typography: Typography = PaylikeTypography,
    shapes: Shapes = PaylikeShapes,
    isSystemUiControllerIncluded: Boolean = false,
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    if (isSystemUiControllerIncluded) {
        systemUiController.setSystemBarsColor(
            color = colors.primaryVariant,
            darkIcons = !isSystemInDarkTheme(),
        )
    }
    CompositionLocalProvider(
        LocalPaylikePaddings provides PaylikePaddings(),
    ) {
        MaterialTheme(
            colors = colors,
            typography = typography,
            shapes = shapes,
            content = content,
        )
    }
}

/** Created based on [MaterialTheme] object */
object PaylikeTheme {
    val colors: Colors
        @Composable @ReadOnlyComposable get() = MaterialTheme.colors

    val typography: Typography
        @Composable @ReadOnlyComposable get() = MaterialTheme.typography

    val shapes: Shapes
        @Composable @ReadOnlyComposable get() = MaterialTheme.shapes

    val paddings: PaylikePaddings
        @Composable @ReadOnlyComposable get() = LocalPaylikePaddings.current
}
