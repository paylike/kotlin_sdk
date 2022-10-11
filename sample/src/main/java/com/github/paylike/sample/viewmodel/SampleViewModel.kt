package com.github.paylike.sample.viewmodel

import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.util.Log
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.paylike.kotlin_client.domain.dto.payment.request.money.PaymentAmount
import com.github.paylike.kotlin_client.domain.dto.payment.request.test.PaymentTestDto
import com.github.paylike.kotlin_engine.model.service.ApiMode
import com.github.paylike.kotlin_engine.viewmodel.PaylikeEngine
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.WhiteLabelFormComposable
import com.github.paylike.kotlin_sdk.simplewhitelabel.viewmodel.WhiteLabelViewModel
import com.github.paylike.sample.BuildConfig
import com.github.paylike.sample.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

/**
 * Sample application viewModel
 */
class SampleViewModel : ViewModel(), Observer {
    /**
     * Engine state machine responsible to execute the Paylike's payment services.
     */
    private val engine: PaylikeEngine =
        PaylikeEngine(
            merchantId = BuildConfig.PaylikeMerchantApiKey,
            apiMode = ApiMode.TEST,
        )

    /** For debug purposes listed available routes */
    private val routes: List<String> =
        listOf(
            "ExampleList",
            "SimpleWhiteLabelExample",
        )

    val scaffoldState = ScaffoldState(DrawerState(DrawerValue.Closed), SnackbarHostState())

    /**
     * Start route of the sample application for the Navigation implementation
     */
    val rootRoute: String = "ExampleList"

    /**
     * Stores every example usage and their needed data for the library "kotlin_sdk"
     */
    val sdkExampleModelMap: Map<String, SdkExampleModel> =
        mapOf(
            Pair(
                "SimpleWhiteLabelExample",
                SdkExampleModel(
                    uiStates = CardStates(
                        isOpen = mutableStateOf(false),
                        iconRotation = mutableStateOf(90f),
                    ),
                    titleId = R.string.simple_white_label_example_title,
                    descriptionId = R.string.simple_white_label_example_description,
                    exampleButtonTextId = R.string.example_show_button_text,
                    exampleComposable = {
                        WhiteLabelFormComposable(viewModel = WhiteLabelViewModel(
                            engine = engine,
                            onPayButton = { cardNumber, cvc, expiryMonth, expiryYear ->
                                val currency = Currency.getInstance("USD")
                                val format = NumberFormat.getCurrencyInstance()
                                format.currency = currency

                                CoroutineScope(Dispatchers.IO).launch {
                                    Log.d(
                                        "onPayButton",
                                        "$cardNumber, $cvc, $expiryMonth, $expiryYear",
                                    )
                                    engine.initializePaymentData(
                                        cardNumber,
                                        cvc,
                                        expiryMonth,
                                        expiryYear + 2000,
                                    )
                                    Log.d(
                                        "onPayButton",
                                        "${engine.repository.paymentRepository?.card ?: "Card data not found"}",
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
                                    Log.d(
                                        "onPayButton",
                                        format.format(engine.repository.paymentRepository?.amount?.calculateValue()) ?: "Amount data not found",
                                    )
                                    engine.startPayment()
                                }
                            }
                        ),
                            scaffoldState = scaffoldState,
                        )
                    },
                )
            )
        )

    init {

    }
    /**
     *
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

    /**
     * Implemented Observer
     */
    override fun update(o: Observable?, arg: Any?) {
        TODO("Not yet implemented")
    }
}
