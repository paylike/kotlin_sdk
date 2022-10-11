package com.github.paylike.kotlin_sdk.simplewhitelabel.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.paylike.kotlin_engine.error.exceptions.WrongTypeOfObservableListened
import com.github.paylike.kotlin_engine.error.exceptions.WrongTypeOfObserverUpdateArg
import com.github.paylike.kotlin_engine.view.PaylikeWebView
import com.github.paylike.kotlin_engine.viewmodel.EngineState
import com.github.paylike.kotlin_engine.viewmodel.PaylikeEngine
import com.github.paylike.kotlin_luhn.PaylikeLuhn
import com.github.paylike.kotlin_sdk.CardBrands
import com.github.paylike.kotlin_sdk.viewmodel.UiState
import java.util.*

open class WhiteLabelViewModel(
    val engine: PaylikeEngine,
    private val onPayButton:
    ((
        cardNumber: String,
        cvc: String,
        expiryMonth: Int,
        expiryYear: Int,
    ) -> Unit)
) : ViewModel(), Observer {
    var uiState by mutableStateOf(UiState())
        private set

    val webView = PaylikeWebView(engine)

    init {
        engine.addObserver(this)
    }

    fun resetIsSuccess() {
        uiState = uiState.copy(
            isSuccess = false
        )
        engine.resetEngineStates()
    }

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
        var canExecute = true
        if (uiState.cardNumber.length < 16 || !PaylikeLuhn.isValid(uiState.cardNumber)) {
            setIsCardNumberValid(false)
            canExecute = false
        }
        if (
            uiState.expiryDate.length < 4 || uiState.expiryDate.substring(0, 2).toIntOrNull()!! > 12
        ) {
            setExpiryDateValid(false)
            canExecute = false
        }
        if (uiState.securityCode.length < 3) {
            setSecurityCodeValid(false)
            canExecute = false
        }
        if (canExecute) {
            onPayButton.invoke(
                uiState.cardNumber,
                uiState.securityCode,
                uiState.expiryDate.substring(0, 2).toInt(),
                uiState.expiryDate.substring(2, 4).toInt(),
            )
        }
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

    override fun update(o: Observable?, arg: Any?) {
        if (o !is PaylikeEngine) {
            throw WrongTypeOfObservableListened(
                observer = this::class.simpleName!!,
                observable =
                if (o != null) {
                    o::class.simpleName!!
                } else {
                    "Anonymous"
                },
            )
        }
        if (arg !is EngineState) {
            throw WrongTypeOfObserverUpdateArg(arg)
        }
        if (arg == EngineState.SUCCESS) {
            uiState = uiState.copy(
                isSuccess = true
            )
        }
    }
}
