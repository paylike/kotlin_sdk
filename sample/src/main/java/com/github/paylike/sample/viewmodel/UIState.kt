package com.github.paylike.sample.viewmodel

data class UIState(
    var cardNumber: String = "",
    var isCardNumberValid: Boolean = true,

    var expiryDate: String = "",
    var isExpiryDateValid:Boolean = true,

    var securityCode: String = "",
    var isSecurityCodeValid: Boolean = true,

    var highlightedCardBrand: CardBrands = CardBrands.NONE
)
