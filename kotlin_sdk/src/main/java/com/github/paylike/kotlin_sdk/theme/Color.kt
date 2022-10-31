package com.github.paylike.kotlin_sdk.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

internal val PaylikeGreen = Color(0xFF2e8f29)
internal val PaylikeLightGreen = Color(0xFF64c057)
internal val PaylikeDarkGreen = Color(0xFF006000)
internal val PaylikeRed = Color(0xFFb82d28)
internal val PaylikeLightRed = Color(0xFFf06051)
internal val PaylikeDarkRed = Color(0xFF810000)
internal val PaylikeErrorRed = Color(0xFFFF0000)

internal val PaylikeDarkColorPalette =
    darkColors(
        primary = PaylikeDarkGreen,
        primaryVariant = PaylikeGreen,
        onPrimary = Color.White,
        secondary = PaylikeDarkRed,
        secondaryVariant = PaylikeRed,
        onSecondary = Color.White,
        //        surface = Color.Black,
        //        onSurface = Color.White,
        //        background = Color.Black,
        //        onBackground = Color.White,
        error = PaylikeErrorRed,
        onError = Color.White,
    )

internal val PaylikeLightColorPalette =
    lightColors(
        primary = PaylikeLightGreen,
        primaryVariant = PaylikeGreen,
        onPrimary = Color.Black,
        secondary = PaylikeLightRed,
        secondaryVariant = PaylikeRed,
        onSecondary = Color.Black,
        //        surface = Color.White,
        //        onSurface = Color.Black,
        //        background = Color.White,
        //        onBackground = Color.Black,
        error = PaylikeErrorRed,
        onError = Color.White,
    )
