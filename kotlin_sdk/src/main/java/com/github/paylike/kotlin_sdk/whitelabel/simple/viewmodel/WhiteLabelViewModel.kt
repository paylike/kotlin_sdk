package com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel

import android.speech.tts.TextToSpeech.Engine
import android.util.Range
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.paylike.kotlin_client.domain.dto.payment.request.card.PaylikeCardDto
import com.github.paylike.kotlin_client.domain.dto.payment.request.money.PaymentAmount
import com.github.paylike.kotlin_client.domain.dto.payment.request.plan.PaymentPlanDto
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.PaymentTestDto
import com.github.paylike.kotlin_client.domain.dto.payment.request.unplanned.PaymentUnplannedDto
import com.github.paylike.kotlin_engine.error.exceptions.WrongTypeOfObservableListened
import com.github.paylike.kotlin_engine.error.exceptions.WrongTypeOfObserverUpdateArg
import com.github.paylike.kotlin_engine.view.PaylikeWebView
import com.github.paylike.kotlin_engine.viewmodel.EngineState
import com.github.paylike.kotlin_engine.viewmodel.PaylikeEngine
import com.github.paylike.kotlin_luhn.PaylikeLuhn
import com.github.paylike.kotlin_sdk.CardNumberField
import com.github.paylike.kotlin_sdk.CardVerificationCodeField
import com.github.paylike.kotlin_sdk.ExpiryDateField
import com.github.paylike.kotlin_sdk.PayButton
import com.github.paylike.kotlin_sdk.cardprovider.SupportedCardProviders
import com.github.paylike.kotlin_sdk.cardprovider.calculateProviderFromNumber
import java.time.YearMonth
import java.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject

/**
 * Responsible to maintain the middleware functionality of the payment flow in Paylike's ecosystem
 *
 * [engine]
 * - [PaylikeEngine] Responsible to manage every payment related functionality.
 *
 * [webView]
 * - [PaylikeWebView] Optionally overridable. Paylike ecosystem provides default implementation
 * capable to cooperate with [engine].
 *
 * [onPayButton]
 * - Callback function to execute when we press the [PayButton]
 */
open class WhiteLabelViewModel(
    protected val engine: PaylikeEngine,
    val webView: PaylikeWebView = PaylikeWebView(engine),
    protected val onPayButton:
    (suspend (
        engine: PaylikeEngine,
        cardNumber: String,
        cvc: String,
        expiryMonth: Int,
        expiryYear: Int,
        extenderFields: List<String>?
    ) -> Unit) =
        { engine, cardNumber, cvc, expiryMonth, expiryYear, _ ->
            engine.addEssentialPaymentData(
                cardNumber,
                cvc,
                expiryMonth,
                expiryYear,
            )
        },
) : ViewModel(), Observer {
    /** Logic fields */
    private var possibleCardNumberLengthRange: Range<Int> = Range(16, 16)
    companion object {
        protected const val expiryDateLength: Int = 4
        protected const val cardVerificationCodeLength: Int = 3
    }

    /** Variable UI states */
    var paymentFormState by mutableStateOf(SimplePaymentFormStateModel())
        private set

    /** Subscribes to listen to the engine's state changes */
    init {
        engine.addObserver(this)
    }

    suspend fun addEssentialPaymentDataToEngine(
        cardNumber: String,
        cvc: String,
        expiryMonth: Int,
        expiryYear: Int
    ) {
        this.engine.addEssentialPaymentData(
            cardNumber,
            cvc,
            expiryMonth,
            expiryYear,
        )
    }

    fun addDescriptionPaymentDataToEngine(
        paymentTestData: PaymentTestDto? = null,
        paymentAmount: PaymentAmount? = null,
        paymentPlanDto: List<PaymentPlanDto>? = null,
        paymentUnplannedDto: PaymentUnplannedDto? = null
    ) {
        engine.addDescriptionPaymentData(
            paymentTestData = paymentTestData,
            paymentAmount = paymentAmount,
            paymentPlanDataList = paymentPlanDto,
            paymentUnplannedData = paymentUnplannedDto,
        )
    }

    fun addAdditionalPaymentDataToEngine(
        textData: String? = null,
        customData: JsonObject? = null,
    ) {
        engine.addAdditionalPaymentData(
            textData = textData,
            customData = customData,
        )
    }

    /** Resets every UI state */
    open fun resetViewModelAndEngine() {
        paymentFormState = SimplePaymentFormStateModel()
        engine.resetEngineStates()
    }

    /** Responsible to sanitise input on [CardNumberField] and sets if card number is acceptable */
    fun onCardNumberChanged(newValue: String) {
        /** Input for the upcoming sanitiser pipeline */
        var modulatedNewValue: String = ""

        /** Drop any non numeric character */
        newValue.forEach { char ->
            modulatedNewValue +=
                if (Regex(pattern = "[0-9]").matches(char.toString())) {
                    char
                } else {
                    ""
                }
        }

        /** Check for the actual possible provider */
        val noticedCardProvider = calculateProviderFromNumber(modulatedNewValue)
        possibleCardNumberLengthRange =
            when (noticedCardProvider) {
                SupportedCardProviders.NONE -> Range(16, 16)
                SupportedCardProviders.MAESTRO -> Range(16, 19)
                SupportedCardProviders.MASTERCARD -> Range(16, 16)
                SupportedCardProviders.VISA -> Range(16, 16)
            }

        /** Set provider to state */
        paymentFormState = paymentFormState.copy(highlightedCardProvider = noticedCardProvider)

        /** Trim input value based on provider */
        if (modulatedNewValue.length > possibleCardNumberLengthRange.upper) {
            modulatedNewValue = modulatedNewValue.substring(0, possibleCardNumberLengthRange.upper)
        }

        /** Set the value and the validity flag */
        paymentFormState = paymentFormState.copy(cardNumber = modulatedNewValue)
        if (possibleCardNumberLengthRange.contains(modulatedNewValue.length)) {
            if (paymentFormState.highlightedCardProvider != SupportedCardProviders.NONE) {
                setIsCardNumberValid(PaylikeLuhn.isValid(modulatedNewValue))
            } else {
                setIsCardNumberValid(false)
            }
        } else {
            setIsCardNumberValid(true)
        }
    }
    protected fun setIsCardNumberValid(newValue: Boolean) {
        paymentFormState = paymentFormState.copy(isCardNumberValid = newValue)
    }
    /** Responsible to sanitise input on [ExpiryDateField] and sets if expiry date is acceptable */
    fun onExpiryDateChanged(newValue: String) {
        /** Input for the upcoming sanitiser pipeline */
        var modulatedNewValue: String = ""

        /** Drop any non numeric character */
        newValue.forEach { char ->
            modulatedNewValue +=
                if (Regex(pattern = "[0-9]").matches(char.toString())) {
                    char
                } else {
                    ""
                }
        }

        /** Trim input value based on provider */
        if (modulatedNewValue.length > expiryDateLength) {
            modulatedNewValue = modulatedNewValue.substring(0, expiryDateLength)
        }

        /** Set the value and Check if month is between range [1..12] then set validity flag */
        paymentFormState = paymentFormState.copy(expiryDate = modulatedNewValue)
        if (modulatedNewValue.length > 1) {
            val isMonthValid = (modulatedNewValue.substring(0, 2).toInt() in 1..12)
            setIsExpiryDateValid(isMonthValid)
            if (modulatedNewValue.length == 4) {
                setIsExpiryDateValid(isDateValid(modulatedNewValue))
            }
        } else {
            setIsExpiryDateValid(true)
        }
    }
    protected fun setIsExpiryDateValid(newValue: Boolean) {
        paymentFormState = paymentFormState.copy(isExpiryDateValid = newValue)
    }
    protected fun isDateValid(inputMonthYear: String): Boolean {
        val rightNowYearMonth = YearMonth.now()
        val inputYearMonth =
            YearMonth.parse("20${inputMonthYear.substring(2,4)}-${inputMonthYear.substring(0,2)}")
        return inputYearMonth >= rightNowYearMonth
    }

    /**
     * Responsible to sanitise input on [CardVerificationCodeField] and sets if CVC is acceptable
     */
    fun onCardVerificationCodeChanged(newValue: String) {
        /** Input for the upcoming sanitiser pipeline */
        var modulatedNewValue: String = ""

        /** Drop any non numeric character */
        newValue.forEach { char ->
            modulatedNewValue +=
                if (Regex(pattern = "[0-9]").matches(char.toString())) {
                    char
                } else {
                    ""
                }
        }

        /** Trim input value based on provider */
        if (modulatedNewValue.length > cardVerificationCodeLength) {
            modulatedNewValue = modulatedNewValue.substring(0, cardVerificationCodeLength)
        }

        /** Set the value */
        paymentFormState = paymentFormState.copy(cardVerificationCode = modulatedNewValue)
        setIsCardVerificationCodeValid(true)
    }
    protected fun setIsCardVerificationCodeValid(newValue: Boolean) {
        paymentFormState = paymentFormState.copy(isCardVerificationCodeValid = newValue)
    }

    /** Checks if the fields are satisfactory then invokes [onPayButton] */
    open fun onPayButtonClick() {
        if (canExecute()) {
            paymentFormState =
                paymentFormState.copy(
                    isPaymentFlowInitiated = true,
                )
            /** We load the collected [PaylikeCardDto] data to the callback */
            CoroutineScope(Dispatchers.IO).launch {
                onPayButton.invoke(
                    engine,
                    paymentFormState.cardNumber,
                    paymentFormState.cardVerificationCode,
                    paymentFormState.expiryDate.substring(0, 2).toInt(),
                    paymentFormState.expiryDate.substring(2, 4).toInt() + 2000,
                    null,
                )
                engine.startPayment()
            }
        }
    }
    /** Checks if the fields contain acceptable input, then if so executes [onPayButton] callback */
    protected fun canExecute(): Boolean {
        var canExecute = true
        if (
            !possibleCardNumberLengthRange.contains(paymentFormState.cardNumber.length) ||
            !PaylikeLuhn.isValid(paymentFormState.cardNumber) ||
            !paymentFormState.isCardNumberValid
        ) {
            setIsCardNumberValid(false)
            canExecute = false
        }
        if (
            paymentFormState.expiryDate.length < expiryDateLength ||
            !paymentFormState.isExpiryDateValid
        ) {
            setIsExpiryDateValid(false)
            canExecute = false
        }
        if (paymentFormState.cardVerificationCode.length < cardVerificationCodeLength) {
            setIsCardVerificationCodeValid(false)
            canExecute = false
        }
        return canExecute
    }

    /** Helper function to determine what state the UI has to be */
    protected fun isInitialState(state: EngineState): Boolean =
        state == EngineState.WAITING_FOR_INPUT
    protected fun isSuccess(state: EngineState): Boolean = state == EngineState.SUCCESS
    protected fun isError(state: EngineState): Boolean = state == EngineState.ERROR

    /**
     * Overrideable callback events called on engine state changes
     *
     * [onWaitingForInput] - Not called on the initialization of the engine, only if it is reset
     *
     * [onWebViewChallengeStarted], [onWebViewChallengeUserInputRequired] - transient state, possibility to hook the flow and implement additional functionality
     *
     * [onSuccess], [onError] - these are the possible final states of the engine, possibility to hook the end of the flow depending on the result
     */
    protected open fun onWaitingForInput() {}
    protected open fun onWebViewChallengeStarted() {}
    protected open fun onWebViewChallengeUserInputRequired() {}
    protected open fun onSuccess() {}
    protected open fun onError() {}

    /**
     * Check if we listen to the right object, receive the right argument and update UI state based
     * on [EngineState].
     */
    override fun update(o: Observable?, arg: Any?) {
        /** Check */
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

        /** Save UI states */
        paymentFormState =
            paymentFormState.copy(
                isInitialState = isInitialState(arg),
                isSuccess = isSuccess(arg),
                isError = isError(arg),
                isPaymentFlowInitiated = !(isInitialState(arg) || isSuccess(arg) || isError(arg)),
                error = if (isError(arg)) o.error else null,
            )

        /**
         * Calls overrideable callback functions on given event
         */
        when (arg) {
            EngineState.WAITING_FOR_INPUT -> onWaitingForInput()
            EngineState.WEBVIEW_CHALLENGE_STARTED -> onWebViewChallengeStarted()
            EngineState.WEBVIEW_CHALLENGE_USER_INPUT_REQUIRED -> onWebViewChallengeUserInputRequired()
            EngineState.SUCCESS -> onSuccess()
            EngineState.ERROR -> onError()
        }
    }
}
