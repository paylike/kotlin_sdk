package com.github.paylike.sample.viewmodel

import androidx.compose.runtime.Composable

/**
 * Data class to store every data for any Paylike's kotlin SDK example usage
 */
data class SdkExampleModel(
    val uiStates: CardStates,
    val titleId: Int,
    val descriptionId: Int,
    val exampleButtonTextId: Int,
    val exampleComposable: @Composable () -> Unit,
)
