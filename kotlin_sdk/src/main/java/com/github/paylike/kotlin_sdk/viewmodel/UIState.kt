package com.github.paylike.kotlin_sdk.viewmodel

import com.github.paylike.kotlin_sdk.CardBrands

data class UIState(
    val cardNumber: String = "",
    val isCardNumberValid: Boolean = true,
    val expiryDate: String = "",
    val isExpiryDateValid: Boolean = true,
    val securityCode: String = "",
    val isSecurityCodeValid: Boolean = true,
    val highlightedCardBrand: CardBrands = CardBrands.NONE
)
