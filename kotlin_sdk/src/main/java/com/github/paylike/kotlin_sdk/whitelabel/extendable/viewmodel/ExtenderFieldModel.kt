package com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel

/**
 */
data class ExtenderFieldModel(
    /** field itself */
    var extenderFieldState: ExtenderFieldState = ExtenderFieldState(),

    /** callback */
    var onExtenderFieldChanged: ((newValue: String) -> Unit) = { newValue ->
        extenderFieldState.field = newValue /* extenderFieldState.copy(
            field = newValue,
        )*/
    } /* = null*/,

    /** flag setter */
    internal var setIsExtenderFieldValid: ((newValue: Boolean) -> Unit)? /* = { newValue ->
        extenderFieldState = extenderFieldState.copy(
            isFieldValid = newValue
        )
    }*/ = null,

    // modifier
    // where to put

    /** UI composable */
    var extenderFieldComposable: /*@Composable () -> */ Unit,
)
