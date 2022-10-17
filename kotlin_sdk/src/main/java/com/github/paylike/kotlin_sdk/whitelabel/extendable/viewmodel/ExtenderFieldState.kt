package com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel

data class ExtenderFieldState(
    var field: String = "", // TODO maybe: val field: MutableState<String>
    var isFieldValid: Boolean = true,
)
