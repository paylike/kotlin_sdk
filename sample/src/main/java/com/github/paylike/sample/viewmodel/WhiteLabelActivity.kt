package com.github.paylike.sample.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.paylike.sample.view.WhiteLabelFormView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WhiteLabelViewModel : ViewModel() {
    var uiState by mutableStateOf(UIState())
        private set

    fun setIsCardNumberValid(newValue: Boolean) {
        uiState.isCardNumberValid = newValue
    }

    fun handleCardNumberInputChange(newValue: String) {
        if (newValue.length <= 16) uiState.cardNumber = newValue
        if (newValue.isEmpty()) {
            uiState.highlightedCardBrand = CardBrands.NONE
            return
        }
    }

    fun setHighlightedCardBrand(cardNumberInput: String) {
        uiState.highlightedCardBrand =
            if (cardNumberInput[0]?.digitToIntOrNull() == 4)
            CardBrands.VISA
            else if (cardNumberInput[0]?.digitToIntOrNull() == 5)
                CardBrands.MASTERCARD
            else CardBrands.NONE
    }
}
