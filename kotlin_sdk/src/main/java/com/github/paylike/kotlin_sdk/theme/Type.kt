package com.github.paylike.kotlin_sdk.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val PaylikeTypography =
    Typography(
        defaultFontFamily = FontFamily.SansSerif,
        h1 =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Light,
                fontSize = 95.sp,
                letterSpacing = (-1.5f).sp,
            ),
        h2 =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Light,
                fontSize = 60.sp,
                letterSpacing = (-0.5f).sp,
            ),
        h3 =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 48.sp,
                letterSpacing = 0.sp,
            ),
        h4 =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 34.sp,
                letterSpacing = (0.25f).sp,
            ),
        h5 =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                letterSpacing = 0.sp,
            ),
        h6 =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                letterSpacing = (0.15f).sp,
            ),
        subtitle1 =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                letterSpacing = (0.15f).sp,
            ),
        subtitle2 =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                letterSpacing = (0.1f).sp,
            ),
        body1 =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                letterSpacing = (0.5f).sp,
            ),
        body2 =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                letterSpacing = (0.25f).sp,
            ),
        button =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                letterSpacing = (1.25f).sp,
            ),
        caption =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                letterSpacing = (0.4f).sp,
            ),
        overline =
            TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 10.sp,
                letterSpacing = (1.5f).sp,
            ),
    )
