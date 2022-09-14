package com.github.paylike.sample.viewmodel

data class UIState(
    val cardNumber: String = "",
    val isCardNumberValid: Boolean = true,

    val expiryDate: String = "",
    val isExpiryDateValid:Boolean = true,

    val securityCode: String = "",
    val isSecurityCodeValid: Boolean = true,

    val highlightedCardBrand: CardBrands = CardBrands.NONE
)
