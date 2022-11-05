package com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

/**
 * Stores any extender field:
 *
 * - [extenderFieldState]
 * - state that keeps the VM consistent
 *
 * - [onChangedPipeLineFunction]
 * - optional callback function for when [extenderFieldState] changes
 *
 * - [extenderFieldComposable]
 * - Predefined paylike composable from paylike ecosystem completely arbitrary
 */
data class ExtenderFieldModel(

    /** Field state */
    val extenderFieldState: MutableState<String>,
    /** Optional [onExtenderFieldChanged] logic */
    val onChangedPipeLineFunction: (String) -> String = { newValue -> newValue },

    /** UI composable */
    val extenderFieldComposable:
        @Composable
        (
            modifier: Modifier,
            value: String,
            textStyle: TextStyle,
            isEnabled: Boolean,
            onValueChanged: (String) -> Unit,
        ) -> Unit,
) {
    /** Callback function for [extenderFieldState] */
    fun onExtenderFieldChanged(newValue: String, onChangedPipeLineFunction: (String) -> String) {
        extenderFieldState.value = onChangedPipeLineFunction(newValue)
    }
}
