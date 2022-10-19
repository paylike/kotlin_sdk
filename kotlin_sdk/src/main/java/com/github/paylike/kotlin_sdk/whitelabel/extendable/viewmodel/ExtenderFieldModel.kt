package com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

/**
 */
data class ExtenderFieldModel(
    /** State */
    //    val extenderFieldState: MutableState<ExtenderFieldState>,

    val extenderFieldState: MutableState<String>,
    val isExtenderFieldStateValid: MutableState<Boolean>,
    val onChangedPipeLineFunction: (String) -> String = { newValue -> newValue },

    /** UI composable */
    val extenderFieldComposable:
        @Composable
        (
            modifier: Modifier,
            value: String,
            textStyle: TextStyle,
            isEnabled: Boolean, // TODO
            onValueChanged: (String) -> Unit,
        ) -> Unit,
) {
    fun onExtenderFieldChanged(newValue: String, onChangedPipeLineFunction: (String) -> String) {
        extenderFieldState.value = onChangedPipeLineFunction(newValue)
    }
}
