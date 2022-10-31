package com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.github.paylike.kotlin_client.domain.dto.payment.request.card.PaylikeCardDto
import com.github.paylike.kotlin_engine.view.PaylikeWebView
import com.github.paylike.kotlin_engine.viewmodel.PaylikeEngine
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExtendableWhiteLabelViewModel(
    engine: PaylikeEngine,
    webView: PaylikeWebView = PaylikeWebView(engine),
    extenderFieldList: MutableList<ExtenderFieldModel> = mutableListOf(),
    onExtendedPayButton:
        (suspend (
            engine: PaylikeEngine,
            cardNumber: String,
            cvc: String,
            expiryMonth: Int,
            expiryYear: Int,
            extenderFields: List<String>?
        ) -> Unit) =
        { engine, cardNumber, cvc, expiryMonth, expiryYear, extenderFields ->
            engine.addEssentialPaymentData(
                cardNumber,
                cvc,
                expiryMonth,
                expiryYear,
            )
        },
) :
    WhiteLabelViewModel(
        engine = engine,
        webView = webView,
        onPayButton = onExtendedPayButton,
    ) {

    /** Variable UI states */
    var extenderPaymentFormStateList by mutableStateOf(extenderFieldList)
        private set

    override fun resetViewModelAndEngine() {
        extenderPaymentFormStateList.forEach { extenderField ->
            extenderField.extenderFieldState.value = ""
        }
        super.resetViewModelAndEngine()
    }

    override fun onPayButtonClick() {
        if (canExecute()) {
            /** We load every extender field to a list */
            val fieldList: MutableList<String> = mutableListOf()
            extenderPaymentFormStateList.forEach { extenderField ->
                fieldList.add(extenderField.extenderFieldState.value)
            }
            /**
             * We load the collected [PaylikeCardDto] and [extenderPaymentFormStateList] variable
             * data to the callback
             */
            CoroutineScope(Dispatchers.IO).launch {
                onPayButton.invoke(
                    engine,
                    paymentFormState.cardNumber,
                    paymentFormState.cardVerificationCode,
                    paymentFormState.expiryDate.substring(0, 2).toInt(),
                    paymentFormState.expiryDate.substring(2, 4).toInt() + 2000,
                    fieldList,
                )
                engine.startPayment()
            }
        }
    }
}
