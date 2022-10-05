package com.github.paylike.kotlin_sdk.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.paylike.kotlin_luhn.PaylikeLuhn
import com.github.paylike.kotlin_sdk.CardBrands

open class BasicViewModel : ViewModel() {
    var uiState by mutableStateOf(UIState())
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
        if (uiState.cardNumber.length < 16 || !PaylikeLuhn.isValid(uiState.cardNumber))
            setIsCardNumberValid(false)
        if (
            uiState.expiryDate.length < 4 || uiState.expiryDate.substring(0, 2).toIntOrNull()!! > 12
        )
            setExpiryDateValid(false)
        if (uiState.securityCode.length < 3) setSecurityCodeValid(false)
    }

    private fun setCardNumber(newValue: String) {
        if (newValue.length <= 16 && !newValue.endsWith(' ') && !newValue.endsWith('\n'))
            uiState = uiState.copy(cardNumber = newValue)
    }

    private fun setHighlightedCardBrand(cardNumberInput: String) {
        uiState =
            uiState.copy(
                highlightedCardBrand =
                    if (cardNumberInput.isEmpty()) CardBrands.NONE
                    else if (cardNumberInput[0].digitToIntOrNull() == 4) CardBrands.VISA
                    else if (cardNumberInput[0].digitToIntOrNull() == 5) CardBrands.MASTERCARD
                    else CardBrands.NONE
            )
    }

    private fun setIsCardNumberValid(newValue: Boolean) {
        uiState = uiState.copy(isCardNumberValid = newValue)
    }

    private fun setExpiryDateValid(newValue: Boolean) {
        uiState = uiState.copy(isExpiryDateValid = newValue)
    }

    private fun setExpiryDate(newValue: String) {
        if (newValue.length <= 4 && !newValue.endsWith(' ') && !newValue.endsWith('\n')) {
            uiState = uiState.copy(expiryDate = newValue)
        }
    }

    private fun setSecurityCodeValid(newValue: Boolean) {
        uiState = uiState.copy(isSecurityCodeValid = newValue)
    }

    private fun setSecurityCode(newValue: String) {
        if (newValue.length <= 3 && !newValue.endsWith(' ') && !newValue.endsWith('\n'))
            uiState = uiState.copy(securityCode = newValue)
    }
}
