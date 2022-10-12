package com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.paylike.kotlin_engine.error.exceptions.WrongTypeOfObservableListened
import com.github.paylike.kotlin_engine.error.exceptions.WrongTypeOfObserverUpdateArg
import com.github.paylike.kotlin_engine.view.PaylikeWebView
import com.github.paylike.kotlin_engine.viewmodel.EngineState
import com.github.paylike.kotlin_engine.viewmodel.PaylikeEngine
import com.github.paylike.kotlin_luhn.PaylikeLuhn
import com.github.paylike.kotlin_sdk.cardprovider.SupportedCardProviders
import java.util.*

open class WhiteLabelViewModel(
    private val engine: PaylikeEngine,
    private val onPayButton:
    ((
        cardNumber: String,
        cvc: String,
        expiryMonth: Int,
        expiryYear: Int,
    ) -> Unit)
) : ViewModel(), Observer {

    var paymentFormState by mutableStateOf(PaymentFormStateModel())
        private set

    val webView = PaylikeWebView(engine)

    init {
        engine.addObserver(this)
    }

    fun resetPaymentFormState() {
        paymentFormState = PaymentFormStateModel()
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
        if (paymentFormState.cardNumber.length < 16 || !PaylikeLuhn.isValid(paymentFormState.cardNumber)) {
            setIsCardNumberValid(false)
            canExecute = false
        }
        if (
            paymentFormState.expiryDate.length < 4 || paymentFormState.expiryDate.substring(0, 2).toIntOrNull()!! > 12
        ) {
            setExpiryDateValid(false)
            canExecute = false
        }
        if (paymentFormState.securityCode.length < 3) {
            setSecurityCodeValid(false)
            canExecute = false
        }
        if (canExecute) {
            onPayButton.invoke(
                paymentFormState.cardNumber,
                paymentFormState.securityCode,
                paymentFormState.expiryDate.substring(0, 2).toInt(),
                paymentFormState.expiryDate.substring(2, 4).toInt(),
            )
        }
    }

    private fun setCardNumber(newValue: String) {
        if (newValue.length <= 16 && !newValue.endsWith(' ') && !newValue.endsWith('\n'))
            paymentFormState = paymentFormState.copy(cardNumber = newValue)
    }

    private fun setHighlightedCardBrand(cardNumberInput: String) {
        paymentFormState =
            paymentFormState.copy(
                highlightedCardBrand =
                when {
                    cardNumberInput.isEmpty() -> SupportedCardProviders.NONE
                    cardNumberInput[0].digitToIntOrNull() == 4 -> SupportedCardProviders.VISA
                    cardNumberInput[0].digitToIntOrNull() == 5 -> SupportedCardProviders.MASTERCARD

                    else -> SupportedCardProviders.NONE
                }
            )
    }

    private fun setIsCardNumberValid(newValue: Boolean) {
        paymentFormState = paymentFormState.copy(isCardNumberValid = newValue)
    }

    private fun setExpiryDateValid(newValue: Boolean) {
        paymentFormState = paymentFormState.copy(isExpiryDateValid = newValue)
    }

    private fun setExpiryDate(newValue: String) {
        if (newValue.length <= 4 && !newValue.endsWith(' ') && !newValue.endsWith('\n')) {
            paymentFormState = paymentFormState.copy(expiryDate = newValue)
        }
    }

    private fun setSecurityCodeValid(newValue: Boolean) {
        paymentFormState = paymentFormState.copy(isSecurityCodeValid = newValue)
    }

    private fun setSecurityCode(newValue: String) {
        if (newValue.length <= 3 && !newValue.endsWith(' ') && !newValue.endsWith('\n'))
            paymentFormState = paymentFormState.copy(securityCode = newValue)
    }

    private fun shouldPayButtonBeVisible(state: EngineState): Boolean = state != EngineState.WAITING_FOR_INPUT
    private fun isEngineFlowFinished(state: EngineState): Boolean = state == EngineState.SUCCESS || state == EngineState.ERROR

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
        paymentFormState = paymentFormState.copy(
            engineState = arg,
            isPayButtonVisible = shouldPayButtonBeVisible(arg),
            isFinished = isEngineFlowFinished(arg),
        )
    }
}
