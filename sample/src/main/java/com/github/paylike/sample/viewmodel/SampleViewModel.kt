package com.github.paylike.sample.viewmodel

import android.os.Build
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.github.paylike.kotlin_client.domain.dto.payment.request.PaymentData
import com.github.paylike.kotlin_client.domain.dto.payment.request.money.PaymentAmount
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.PaymentTestDto
import com.github.paylike.kotlin_engine.model.service.ApiMode
import com.github.paylike.kotlin_engine.viewmodel.PaylikeEngine
import com.github.paylike.kotlin_sdk.NoteField
import com.github.paylike.kotlin_sdk.paylikeStyle.view.PaylikeStyleExtendableWhiteLabelComposable
import com.github.paylike.kotlin_sdk.paylikeStyle.view.PaylikeStyleSimpleWhiteLabelComposable
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.whitelabel.extendable.view.ExtendableWhiteLabelComposable
import com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel.ExtendableWhiteLabelViewModel
import com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel.ExtenderFieldModel
import com.github.paylike.kotlin_sdk.whitelabel.simple.view.WhiteLabelComposable
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel
import com.github.paylike.sample.BuildConfig
import com.github.paylike.sample.R
import com.github.paylike.sample.view.ErrorCasePicker
import com.github.paylike.sample.view.LocalePicker

/** Sample application viewModel */
class SampleViewModel : ViewModel() {

    /** Scaffold state stored for consistency between configuration changes */
    val scaffoldState = ScaffoldState(DrawerState(DrawerValue.Closed), SnackbarHostState())

    /** Start route of the sample application for the Navigation implementation */
    val rootRoute: String = "ExampleList"

    /** Stores every example usage and their needed data for the library "kotlin_sdk" */
    val sdkExampleModelMap: Map<String, SdkExampleModel>

    /**
     * Defined extender content to show how to define and add it to [ExtendableWhiteLabelComposable]
     * or [PaylikeStyleExtendableWhiteLabelComposable]
     */
    private var extenderContentNote: ExtenderFieldModel

    private var paymentTestDtoEntry by
        mutableStateOf(Pair("Unsupported card scheme", errorCases["Unsupported card scheme"]!!))

    private fun onPaymentTestDtoEntryChanged(newValue: Pair<String, PaymentTestDto>) {
        Log.d("hello", "onchanged")
        paymentTestDtoEntry = newValue
    }

    init {
        extenderContentNote =
            ExtenderFieldModel(
                extenderFieldState = mutableStateOf(""),
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

        sdkExampleModelMap =
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
                        paymentData =
                            PaymentData(
                                test = PaymentTestDto(),
                                amount =
                                    PaymentAmount(
                                        "EUR",
                                        1L,
                                        0,
                                    ),
                            ),
                        exampleViewModel =
                            WhiteLabelViewModel(
                                engine =
                                    PaylikeEngine(
                                        merchantId = BuildConfig.PaylikeMerchantApiKey,
                                        apiMode = ApiMode.TEST,
                                    ),
                            ),
                        exampleComposable = { exampleViewModel, paymentData ->
                            SideEffect {
                                exampleViewModel.resetViewModelAndEngine()

                                exampleViewModel.addDescriptionPaymentDataToEngine(
                                    paymentTestData = paymentData.test,
                                    paymentAmount = paymentData.amount,
                                )
                            }
                            WhiteLabelComposable(
                                modifier = Modifier,
                                viewModel = exampleViewModel,
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
                        paymentData =
                            PaymentData(
                                test = PaymentTestDto(),
                                amount =
                                    PaymentAmount(
                                        "EUR",
                                        1L,
                                        0,
                                    ),
                            ),
                        exampleViewModel =
                            ExtendableWhiteLabelViewModel(
                                engine =
                                    PaylikeEngine(
                                        merchantId = BuildConfig.PaylikeMerchantApiKey,
                                        apiMode = ApiMode.TEST,
                                    ),
                                extenderFieldList =
                                    mutableListOf(
                                        this.extenderContentNote,
                                    ),
                                onExtendedPayButton = {
                                    engine,
                                    cardNumber,
                                    cvc,
                                    expiryMonth,
                                    expiryYear,
                                    extenderFields ->
                                    engine.addEssentialPaymentData(
                                        cardNumber,
                                        cvc,
                                        expiryMonth,
                                        expiryYear,
                                    )
                                    engine.addAdditionalPaymentData(textData = extenderFields!![0])
                                },
                            ),
                        exampleComposable = { exampleViewModel, paymentData ->
                            SideEffect {
                                exampleViewModel.resetViewModelAndEngine()

                                exampleViewModel.addDescriptionPaymentDataToEngine(
                                    paymentTestData = paymentData.test,
                                    paymentAmount = paymentData.amount,
                                )
                            }
                            ExtendableWhiteLabelComposable(
                                modifier = Modifier,
                                viewModel = exampleViewModel as ExtendableWhiteLabelViewModel,
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
                        paymentData =
                            PaymentData(
                                test = PaymentTestDto(),
                                amount =
                                    PaymentAmount(
                                        "EUR",
                                        1L,
                                        0,
                                    ),
                            ),
                        exampleViewModel =
                            WhiteLabelViewModel(
                                engine =
                                    PaylikeEngine(
                                        merchantId = BuildConfig.PaylikeMerchantApiKey,
                                        apiMode = ApiMode.TEST,
                                    ),
                            ),
                        exampleComposable = { exampleViewModel, paymentData ->
                            SideEffect {
                                exampleViewModel.resetViewModelAndEngine()

                                exampleViewModel.addDescriptionPaymentDataToEngine(
                                    paymentTestData = paymentData.test,
                                    paymentAmount = paymentData.amount,
                                )
                            }
                            PaylikeStyleSimpleWhiteLabelComposable(
                                modifier = Modifier,
                                viewModel = exampleViewModel,
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
                        paymentData =
                            PaymentData(
                                test = PaymentTestDto(),
                                amount =
                                    PaymentAmount(
                                        "EUR",
                                        1L,
                                        0,
                                    ),
                            ),
                        exampleViewModel =
                            ExtendableWhiteLabelViewModel(
                                engine =
                                    PaylikeEngine(
                                        merchantId = BuildConfig.PaylikeMerchantApiKey,
                                        apiMode = ApiMode.TEST,
                                    ),
                                extenderFieldList =
                                    mutableListOf(
                                        this.extenderContentNote,
                                    ),
                                onExtendedPayButton = {
                                    engine,
                                    cardNumber,
                                    cvc,
                                    expiryMonth,
                                    expiryYear,
                                    extenderFields ->
                                    engine.addEssentialPaymentData(
                                        cardNumber,
                                        cvc,
                                        expiryMonth,
                                        expiryYear,
                                    )
                                    engine.addAdditionalPaymentData(textData = extenderFields!![0])
                                },
                            ),
                        exampleComposable = { exampleViewModel, paymentData ->
                            SideEffect {
                                exampleViewModel.resetViewModelAndEngine()

                                exampleViewModel.addDescriptionPaymentDataToEngine(
                                    paymentTestData = paymentData.test,
                                    paymentAmount = paymentData.amount,
                                )
                            }
                            PaylikeStyleExtendableWhiteLabelComposable(
                                modifier = Modifier,
                                viewModel = exampleViewModel as ExtendableWhiteLabelViewModel,
                            )
                        },
                    )
                ),
                Pair(
                    "ErrorExample",
                    SdkExampleModel(
                        uiStates =
                            CardStates(
                                isOpen = mutableStateOf(false),
                                iconRotation = mutableStateOf(90f),
                            ),
                        titleId = R.string.error_and_localisation_example_title,
                        descriptionId = R.string.error_and_localisation_example_description,
                        exampleButtonTextId = R.string.example_show_button_text,
                        paymentData =
                            PaymentData(
                                /** Included test object to imitate card data error */
                                amount =
                                    PaymentAmount(
                                        "EUR",
                                        1L,
                                        0,
                                    ),
                            ),
                        exampleViewModel =
                            WhiteLabelViewModel(
                                engine =
                                    PaylikeEngine(
                                        merchantId = BuildConfig.PaylikeMerchantApiKey,
                                        apiMode = ApiMode.TEST,
                                    ),
                            ),
                        exampleComposable = { exampleViewModel, paymentData ->
                            SideEffect {
                                exampleViewModel.resetViewModelAndEngine()
                                exampleViewModel.addDescriptionPaymentDataToEngine(
                                    paymentTestData = paymentTestDtoEntry.second,
                                    paymentAmount = paymentData.amount,
                                )
                            }

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                LocalePicker(
                                    modifier = Modifier.padding(PaylikeTheme.paddings.smallPadding),
                                )
                            }
                            ErrorCasePicker(
                                modifier = Modifier.padding(PaylikeTheme.paddings.smallPadding),
                                errorCase = paymentTestDtoEntry,
                                onErrorCaseChanged = { onPaymentTestDtoEntryChanged(it) },
                            )

                            PaylikeStyleSimpleWhiteLabelComposable(
                                modifier = Modifier,
                                viewModel = exampleViewModel,
                            )
                        },
                    )
                ),
            )
    }

    /** Handles the expansion of the example cards */
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
