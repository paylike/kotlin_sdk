package com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel

import androidx.compose.runtime.MutableState

data class ExtenderFieldState(
    val field: MutableState<String>,
    val isValid: MutableState<Boolean>,
)
