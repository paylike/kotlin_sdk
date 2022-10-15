package com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel

import androidx.compose.runtime.Composable

/**
 *
 */
data class ExtenderFieldModel(
    val extenderField: String,
    val extenderFieldState: ExtenderFieldState,
    // isValid
    // callback
    // modifier
    // where to put
    val extenderFieldComposable: @Composable () -> Unit,
)
