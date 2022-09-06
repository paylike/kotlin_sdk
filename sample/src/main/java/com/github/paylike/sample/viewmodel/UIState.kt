package com.github.paylike.sample.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.github.paylike.sample.CardBrands

sealed class UIState {
    var cardNumber = ""
    var isCardNumberValid = false

    var expiryDate = ""
    var isExpiryDateValid = false

    var securityCode = ""
    var isSecurityCodeValid = false

    var highlightedCardBrand = CardBrands.NONE
}