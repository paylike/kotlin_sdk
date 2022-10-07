package com.github.paylike.sample.viewmodel

import androidx.compose.runtime.MutableState

/**
 * Stores the necessary data to maintain consistent UI presentation
 * over configuration changes for any given example usage
 */
data class CardStates(
    val isOpen: MutableState<Boolean>,
    val iconRotation: MutableState<Float>,
)
