package com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.github.paylike.kotlin_engine.view.PaylikeWebView
import com.github.paylike.kotlin_engine.viewmodel.PaylikeEngine
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel
import java.util.*

class ExtendableWhiteLabelViewModel(
    engine: PaylikeEngine,
    webView: PaylikeWebView = PaylikeWebView(engine),
    extenderFieldList: MutableList<ExtenderFieldModel> = mutableListOf(),
    onExtendedPayButton:
        ((
            cardNumber: String,
            cvc: String,
            expiryMonth: Int,
            expiryYear: Int,
            extenderFields: List<String>? // TODO() extender stuff, rather map
        ) -> Unit),
) :
    WhiteLabelViewModel(
        engine = engine,
        webView = webView,
        onPayButton = onExtendedPayButton,
    ) {

    /** Variable UI states */
    var extenderPaymentFormStateList by mutableStateOf(extenderFieldList) // TODO Maybe map?
        private set

    override fun resetPaymentFormState() {
        extenderPaymentFormStateList.forEach { extenderField ->
            extenderField.extenderFieldState.value = ""
        }
        super.resetPaymentFormState()
    }

    override fun onPayButtonClick() {
        val fieldList: MutableList<String> = mutableListOf()
        extenderPaymentFormStateList.forEach { extenderField ->
            fieldList.add(extenderField.extenderFieldState.value)
        }
        if (canExecute()) {
            onPayButton.invoke(
                paymentFormState.cardNumber,
                paymentFormState.cardVerificationCode,
                paymentFormState.expiryDate.substring(0, 2).toInt(),
                paymentFormState.expiryDate.substring(2, 4).toInt(),
                fieldList,
            )
        }
    }

    /** Not sure if we need to overwrite */
    override fun update(o: Observable?, arg: Any?) {
        super.update(o, arg)
    }
}
