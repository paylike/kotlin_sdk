package com.github.paylike.sample.viewmodel

data class UIState(
    var cardNumber: String = "",
    var isCardNumberValid: Boolean = false,

    var expiryDate: String = "",
    var isExpiryDateValid:Boolean = false,

    var securityCode: String = "",
    var isSecurityCodeValid: Boolean = false,

    var highlightedCardBrand: CardBrands = CardBrands.NONE
)
