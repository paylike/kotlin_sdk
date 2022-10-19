package com.github.paylike.sample.viewmodel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.github.paylike.kotlin_client.domain.dto.payment.request.money.PaymentAmount
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.PaymentTestDto
import com.github.paylike.kotlin_engine.model.service.ApiMode
import com.github.paylike.kotlin_engine.viewmodel.PaylikeEngine
import com.github.paylike.kotlin_sdk.NoteField
import com.github.paylike.kotlin_sdk.paylikeStyle.view.PaylikeStyleExtendableWhiteLabelComposable
import com.github.paylike.kotlin_sdk.paylikeStyle.view.PaylikeStyleSimpleWhiteLabelComposable
import com.github.paylike.kotlin_sdk.whitelabel.extendable.view.ExtendableWhiteLabelComposable
import com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel.ExtendableWhiteLabelViewModel
import com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel.ExtenderFieldModel
import com.github.paylike.kotlin_sdk.whitelabel.simple.view.WhiteLabelComposable
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel
import com.github.paylike.sample.BuildConfig
import com.github.paylike.sample.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** Sample application viewModel */
class SampleViewModel : ViewModel() {
    /** Engine state machine responsible to execute the Paylike's payment services. */
    val engine: PaylikeEngine =
        PaylikeEngine(
            merchantId = BuildConfig.PaylikeMerchantApiKey,
            apiMode = ApiMode.TEST,
        )

    /** For debug purposes listed available routes */
    private val routes: List<String> =
        listOf(
            "ExampleList",
            "SimpleWhiteLabelExample",
            "ExtendableWhiteLabelExample",
            "PaylikeStyleWhiteLabelExample",
            "PaylikeStyleExtendableWhiteLabelExample"
        )
    /** Start route of the sample application for the Navigation implementation */
    val rootRoute: String = "ExampleList"

    private var extenderContentNote =
        ExtenderFieldModel(
            extenderFieldState = mutableStateOf(""),
            isExtenderFieldStateValid = mutableStateOf(true),
            extenderFieldComposable = { modifier, value, textStyle, isEnabled, onValueChanged ->
                NoteField(
                    modifier = modifier,
                    value = value,
                    textStyle = textStyle,
                    isEnabled = isEnabled,
                    onValueChanged = onValueChanged,
                )
            }
        )

    /** Stores every example usage and their needed data for the library "kotlin_sdk" */
    val sdkExampleModelMap: Map<String, SdkExampleModel> =
        mapOf(
            Pair(
                "SimpleWhiteLabelExample",
                SdkExampleModel(
                    uiStates =
                        CardStates(
                            isOpen = mutableStateOf(false),
                            iconRotation = mutableStateOf(90f),
                        ),
                    titleId = R.string.simple_white_label_example_title,
                    descriptionId = R.string.simple_white_label_example_description,
                    exampleButtonTextId = R.string.example_show_button_text,
                    exampleComposable = {
                        WhiteLabelComposable(
                            modifier = Modifier.fillMaxSize(),
                            viewModel =
                                WhiteLabelViewModel(
                                    engine = engine,
                                    onPayButton = { cardNumber, cvc, expiryMonth, expiryYear, _ ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            engine.initializePaymentData(
                                                cardNumber,
                                                cvc,
                                                expiryMonth,
                                                expiryYear + 2000,
                                            )
                                            engine.addPaymentDescriptionData(
                                                paymentAmount =
                                                    PaymentAmount(
                                                        "EUR",
                                                        1L,
                                                        0,
                                                    ),
                                                paymentTestData = PaymentTestDto(),
                                            )
                                            engine.startPayment()
                                        }
                                    },
                                ),
                        )
                    },
                )
            ),
            Pair(
                "ExtendableWhiteLabelExample",
                SdkExampleModel(
                    uiStates =
                        CardStates(
                            isOpen = mutableStateOf(false),
                            iconRotation = mutableStateOf(90f),
                        ),
                    titleId = R.string.extendable_white_label_example_title,
                    descriptionId = R.string.extendable_white_label_example_description,
                    exampleButtonTextId = R.string.example_show_button_text,
                    exampleComposable = {
                        ExtendableWhiteLabelComposable(
                            modifier = Modifier.fillMaxSize(),
                            viewModel =
                                ExtendableWhiteLabelViewModel(
                                    engine = engine,
                                    extenderFieldList =
                                        mutableListOf(
                                            extenderContentNote,
                                        ),
                                    onExtendedPayButton = {
                                        cardNumber,
                                        cvc,
                                        expiryMonth,
                                        expiryYear,
                                        extenderFields ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            engine.initializePaymentData(
                                                cardNumber,
                                                cvc,
                                                expiryMonth,
                                                expiryYear + 2000,
                                            )
                                            engine.addPaymentDescriptionData(
                                                paymentAmount =
                                                    PaymentAmount(
                                                        "EUR",
                                                        1L,
                                                        0,
                                                    ),
                                                paymentTestData = PaymentTestDto(),
                                            )
                                            if (!extenderFields.isNullOrEmpty()) {
                                                engine.addPaymentAdditionalData(extenderFields[0])
                                            }
                                            engine.startPayment()
                                        }
                                    },
                                ),
                        )
                    },
                )
            ),
            Pair(
                "PaylikeStyleWhiteLabelExample",
                SdkExampleModel(
                    uiStates =
                        CardStates(
                            isOpen = mutableStateOf(false),
                            iconRotation = mutableStateOf(90f),
                        ),
                    titleId = R.string.paylike_style_white_label_example_title,
                    descriptionId = R.string.paylike_style_white_label_example_description,
                    exampleButtonTextId = R.string.example_show_button_text,
                    exampleComposable = {
                        PaylikeStyleSimpleWhiteLabelComposable(
                            modifier = Modifier.fillMaxSize(),
                            viewModel =
                                WhiteLabelViewModel(
                                    engine = engine,
                                    onPayButton = { cardNumber, cvc, expiryMonth, expiryYear, _ ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            engine.initializePaymentData(
                                                cardNumber,
                                                cvc,
                                                expiryMonth,
                                                expiryYear + 2000,
                                            )
                                            engine.addPaymentDescriptionData(
                                                paymentAmount =
                                                    PaymentAmount(
                                                        "EUR",
                                                        1L,
                                                        0,
                                                    ),
                                                paymentTestData = PaymentTestDto(),
                                            )
                                            engine.startPayment()
                                        }
                                    },
                                )
                        )
                    },
                )
            ),
            Pair(
                "PaylikeStyleExtendableWhiteLabelExample",
                SdkExampleModel(
                    uiStates =
                        CardStates(
                            isOpen = mutableStateOf(false),
                            iconRotation = mutableStateOf(90f),
                        ),
                    titleId = R.string.paylike_style_extendable_white_label_example_title,
                    descriptionId =
                        R.string.paylike_style_extendable_white_label_example_description,
                    exampleButtonTextId = R.string.example_show_button_text,
                    exampleComposable = {
                        PaylikeStyleExtendableWhiteLabelComposable(
                            modifier = Modifier.fillMaxSize(),
                            viewModel =
                                ExtendableWhiteLabelViewModel(
                                    engine = engine,
                                    extenderFieldList =
                                        mutableListOf(
                                            extenderContentNote,
                                        ),
                                    onExtendedPayButton = {
                                        cardNumber,
                                        cvc,
                                        expiryMonth,
                                        expiryYear,
                                        extenderFields ->
                                        CoroutineScope(Dispatchers.IO).launch {
                                            engine.initializePaymentData(
                                                cardNumber,
                                                cvc,
                                                expiryMonth,
                                                expiryYear + 2000,
                                            )
                                            engine.addPaymentDescriptionData(
                                                paymentAmount =
                                                    PaymentAmount(
                                                        "EUR",
                                                        1L,
                                                        0,
                                                    ),
                                                paymentTestData = PaymentTestDto(),
                                            )
                                            if (!extenderFields.isNullOrEmpty()) {
                                                engine.addPaymentAdditionalData(extenderFields[0])
                                            }
                                            engine.startPayment()
                                        }
                                    },
                                )
                        )
                    },
                )
            )
        )

    /**
     */
    fun toggleCard(key: String) {
        val newIsOpenValue = !sdkExampleModelMap[key]!!.uiStates.isOpen.value
        sdkExampleModelMap[key]!!.uiStates.isOpen.value = newIsOpenValue
        sdkExampleModelMap[key]!!.uiStates.iconRotation.value =
            if (newIsOpenValue) {
                0f
            } else {
                90f
            }
    }
}

/** // TODO example to format currency to locale */
// val currency = Currency.getInstance("USD")
// val format = NumberFormat.getCurrencyInstance()
// format.currency = currency
