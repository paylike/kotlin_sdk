package com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel

import com.github.paylike.kotlin_engine.viewmodel.EngineState
import com.github.paylike.kotlin_sdk.cardprovider.SupportedCardProviders

data class PaymentFormStateModel(
    val cardNumber: String = "",
    val isCardNumberValid: Boolean = true,

    val expiryDate: String = "",
    val isExpiryDateValid: Boolean = true,

    val securityCode: String = "",
    val isSecurityCodeValid: Boolean = true,

    val highlightedCardBrand: SupportedCardProviders = SupportedCardProviders.NONE,

    val isPayButtonVisible: Boolean = true,
    val isFinished: Boolean = false,

    val engineState: EngineState = EngineState.WAITING_FOR_INPUT,
)
