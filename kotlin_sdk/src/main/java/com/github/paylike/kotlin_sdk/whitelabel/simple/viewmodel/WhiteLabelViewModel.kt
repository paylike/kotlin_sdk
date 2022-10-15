package com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.paylike.kotlin_client.domain.dto.payment.request.card.PaylikeCardDto
import com.github.paylike.kotlin_engine.error.exceptions.WrongTypeOfObservableListened
import com.github.paylike.kotlin_engine.error.exceptions.WrongTypeOfObserverUpdateArg
import com.github.paylike.kotlin_engine.view.PaylikeWebView
import com.github.paylike.kotlin_engine.viewmodel.EngineState
import com.github.paylike.kotlin_engine.viewmodel.PaylikeEngine
import com.github.paylike.kotlin_luhn.PaylikeLuhn
import com.github.paylike.kotlin_sdk.CardNumberField
import com.github.paylike.kotlin_sdk.ExpiryDateField
import com.github.paylike.kotlin_sdk.CardVerificationCodeField
import com.github.paylike.kotlin_sdk.cardprovider.SupportedCardProviders
import com.github.paylike.kotlin_sdk.cardprovider.calculateProviderFromNumber
import java.util.*
import com.github.paylike.kotlin_sdk.PayButton

/**
 * Responsible to maintain the middleware functionality of the payment flow in Paylike's ecosystem
 *
 * [engine] - [PaylikeEngine]
 * Responsible to manage every payment related functionality.
 *
 * [webView] - [PaylikeWebView] Optionally overridable.
 * Paylike ecosystem provides default implementation capable to cooperate with [engine].
 *
 * [onPayButton] - Callback function to execute when we press the [PayButton]
 */
open class WhiteLabelViewModel(
    private val engine: PaylikeEngine,
    val webView: PaylikeWebView = PaylikeWebView(engine),
    val onPayButton:
    ((
        cardNumber: String,
        cvc: String,
        expiryMonth: Int,
        expiryYear: Int,
    ) -> Unit)
) : ViewModel(), Observer {
    /**
     * Logic fields
     */
    protected var possibleCardNumberLength: Int = 16
    companion object {
        protected const val expiryDateLength: Int = 4
        protected const val cardVerificationCodeLength: Int = 3
    }

    /**
     * Variable UI states
     */
    var paymentFormState by mutableStateOf(SimplePaymentFormStateModel())
        private set

    /**
     * Subscribes to listen to the engine's state changes
     */
    init {
        engine.addObserver(this)
    }

    /**
     * Resets every UI state
     */
    open fun resetPaymentFormState() {
        paymentFormState = SimplePaymentFormStateModel()
        engine.resetEngineStates()
    }

    /**
     * Responsible to sanitise input on [CardNumberField] and
     * sets if card number is acceptable
     */
    fun onCardNumberChanged(newValue: String) {
        /**
         * Input for the upcoming sanitiser pipeline
         */
        var modulatedNewValue: String = ""

        /**
         * Drop any non numeric character
         */
        newValue.forEach { char ->
            modulatedNewValue +=
                if (Regex(pattern = "[0-9]").matches(char.toString())) {
                    char
                } else {
                    ""
                }
        }

        /**
         * Check for the actual possible provider
         */
        val noticedCardProvider = calculateProviderFromNumber(modulatedNewValue)
        possibleCardNumberLength = when (noticedCardProvider) {
            SupportedCardProviders.NONE -> 16
            SupportedCardProviders.MAESTRO -> 19
            SupportedCardProviders.MASTERCARD -> 16
            SupportedCardProviders.VISA -> 16
        }

        /**
         * Set provider to state
         */
        paymentFormState = paymentFormState.copy(
            highlightedCardProvider = noticedCardProvider
        )

        /**
         * Trim input value based on provider
         */
        if (modulatedNewValue.length > possibleCardNumberLength) {
            modulatedNewValue = modulatedNewValue.substring(0, possibleCardNumberLength)
        }

        /**
         * Set the value and the validity flag
         */
        paymentFormState = paymentFormState.copy(
            cardNumber = modulatedNewValue
        )
        if (modulatedNewValue.length > 15) { // TODO(" one of the luhn check is not necessary")
            setIsCardNumberValid(PaylikeLuhn.isValid(modulatedNewValue))
        } else {
            setIsCardNumberValid(true)
        }
    }
    protected fun setIsCardNumberValid(newValue: Boolean) {
        paymentFormState = paymentFormState.copy(
            isCardNumberValid = newValue
        )
    }
    /**
     * Responsible to sanitise input on [ExpiryDateField] and
     * sets if expiry date is acceptable
     */
    fun onExpiryDateChanged(newValue: String) {
        /**
         * Input for the upcoming sanitiser pipeline
         */
        var modulatedNewValue: String = ""

        /**
         * Drop any non numeric character
         */
        newValue.forEach { char ->
            modulatedNewValue +=
                if (Regex(pattern = "[0-9]").matches(char.toString())) {
                    char
                } else {
                    ""
                }
        }

        /**
         * Trim input value based on provider
         */
        if (modulatedNewValue.length > expiryDateLength) {
            modulatedNewValue = modulatedNewValue.substring(0, expiryDateLength)
        }

        /**
         * Set the value and Check if month is between range [1..12] then set validity flag
         */
        paymentFormState = paymentFormState.copy(
            expiryDate = modulatedNewValue
        )
        if (modulatedNewValue.length > 1) {
            val isMonthValid = (modulatedNewValue.substring(0, 2).toInt() in 1..12)
            setIsExpiryDateValid(isMonthValid)
        } else {
            setIsExpiryDateValid(true)
        }
    }
    protected fun setIsExpiryDateValid(newValue: Boolean) {
        paymentFormState = paymentFormState.copy(isExpiryDateValid = newValue)
    }
    /**
     * Responsible to sanitise input on [CardVerificationCodeField] and
     * sets if CVC is acceptable
     */
    fun onCardVerificationCodeChanged(newValue: String) {
        /**
         * Input for the upcoming sanitiser pipeline
         */
        var modulatedNewValue: String = ""

        /**
         * Drop any non numeric character
         */
        newValue.forEach { char ->
            modulatedNewValue +=
                if (Regex(pattern = "[0-9]").matches(char.toString())) {
                    char
                } else {
                    ""
                }
        }

        /**
         * Trim input value based on provider
         */
        if (modulatedNewValue.length > cardVerificationCodeLength) {
            modulatedNewValue = modulatedNewValue.substring(0, cardVerificationCodeLength)
        }

        /**
         * Set the value
         */
        paymentFormState = paymentFormState.copy(
            cardVerificationCode = modulatedNewValue
        )
        setIsCardVerificationCodeValid(true)
    }
    protected fun setIsCardVerificationCodeValid(newValue: Boolean) {
        paymentFormState = paymentFormState.copy(isCardVerificationCodeValid = newValue)
    }

    /**
     * Checks if the fields are satisfactory then invokes [onPayButton]
     */
    // TODO create a open wrapper for the check maybe?
    open fun onPayButtonClick() {
        var canExecute = true
        if (
            paymentFormState.cardNumber.length < possibleCardNumberLength &&
            !PaylikeLuhn.isValid(paymentFormState.cardNumber) // TODO(" one of the Luhn check is not necessary")
        ) {
            setIsCardNumberValid(false)
            canExecute = false
        }
        if (
            paymentFormState.expiryDate.length < expiryDateLength
        ) {
            setIsExpiryDateValid(false)
            canExecute = false
        }
        if (
            paymentFormState.cardVerificationCode.length < cardVerificationCodeLength
        ) {
            setIsCardVerificationCodeValid(false)
            canExecute = false
        }
        if (
            canExecute
        ) {
            /**
             * We load the collected [PaylikeCardDto] data to the callback
             */
            onPayButton.invoke(
                paymentFormState.cardNumber,
                paymentFormState.cardVerificationCode,
                paymentFormState.expiryDate.substring(0, 2).toInt(),
                paymentFormState.expiryDate.substring(2, 4).toInt(),
            )
        }
    }

    /**
     * Helper function to determine what state the UI has to be
     */
    protected fun isInitialState(state: EngineState): Boolean = state == EngineState.WAITING_FOR_INPUT
    protected fun isFinalState(state: EngineState): Boolean = state == EngineState.SUCCESS || state == EngineState.ERROR

    /**
     * Check if we listen to the right object, receive the right argument
     * and update UI state based on [EngineState].
     */
    override fun update(o: Observable?, arg: Any?) {
        /**
         * Check
         */
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

        /**
         * Save UI states
         */
        paymentFormState = paymentFormState.copy(
            isInitialState = isInitialState(arg),
            isFinished = isFinalState(arg),
        )

        /**
         * Resets itself when finished
         */
        if (paymentFormState.isFinished) {
            resetPaymentFormState()
        }
    }
}
