package com.github.paylike.kotlin_sdk.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.paylike.kotlin_luhn.PaylikeLuhn
import com.github.paylike.kotlin_sdk.CardBrands

open class BasicViewModel : ViewModel() {
    var BasicUIState by mutableStateOf(BasicUIState())
        private set

    fun handleCardInputChange(newValue: String) {
        setIsCardNumberValid(true)
        setCardNumber(newValue)
        setHighlightedCardBrand(newValue)
    }

    fun handleExpirationInputChange(expirationInput: String) {
        setExpiryDateValid(true)
        setExpiryDate(expirationInput)
    }

    fun handleSecurityCodeChange(input: String) {
        setSecurityCodeValid(true)
        setSecurityCode(input)
    }

    open fun handleButtonClick() {
        if (BasicUIState.cardNumber.length < 16 || !PaylikeLuhn.isValid(BasicUIState.cardNumber))
            setIsCardNumberValid(false)
        if (
            BasicUIState.expiryDate.length < 4 || BasicUIState.expiryDate.substring(0, 2).toIntOrNull()!! > 12
        )
            setExpiryDateValid(false)
        if (BasicUIState.securityCode.length < 3) setSecurityCodeValid(false)
    }

    private fun setCardNumber(newValue: String) {
        if (newValue.length <= 16 && !newValue.endsWith(' ') && !newValue.endsWith('\n'))
            BasicUIState = BasicUIState.copy(cardNumber = newValue)
    }

    private fun setHighlightedCardBrand(cardNumberInput: String) {
        BasicUIState =
            BasicUIState.copy(
                highlightedCardBrand =
                    if (cardNumberInput.isEmpty()) CardBrands.NONE
                    else if (cardNumberInput[0].digitToIntOrNull() == 4) CardBrands.VISA
                    else if (cardNumberInput[0].digitToIntOrNull() == 5) CardBrands.MASTERCARD
                    else CardBrands.NONE
            )
    }

    private fun setIsCardNumberValid(newValue: Boolean) {
        BasicUIState = BasicUIState.copy(isCardNumberValid = newValue)
    }

    private fun setExpiryDateValid(newValue: Boolean) {
        BasicUIState = BasicUIState.copy(isExpiryDateValid = newValue)
    }

    private fun setExpiryDate(newValue: String) {
        if (newValue.length <= 4 && !newValue.endsWith(' ') && !newValue.endsWith('\n')) {
            BasicUIState = BasicUIState.copy(expiryDate = newValue)
        }
    }

    private fun setSecurityCodeValid(newValue: Boolean) {
        BasicUIState = BasicUIState.copy(isSecurityCodeValid = newValue)
    }

    private fun setSecurityCode(newValue: String) {
        if (newValue.length <= 3 && !newValue.endsWith(' ') && !newValue.endsWith('\n'))
            BasicUIState = BasicUIState.copy(securityCode = newValue)
    }
}
