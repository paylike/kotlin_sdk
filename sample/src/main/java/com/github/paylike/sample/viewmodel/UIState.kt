package com.github.paylike.sample.viewmodel

class UIState {
    var cardNumber = ""
    var isCardNumberValid = false

    var expiryDate = ""
    var isExpiryDateValid = false

    var securityCode = ""
    var isSecurityCodeValid = false

    var highlightedCardBrand = CardBrands.NONE
}