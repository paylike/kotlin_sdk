package com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.github.paylike.kotlin_engine.view.PaylikeWebView
import com.github.paylike.kotlin_engine.viewmodel.PaylikeEngine
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.SimplePaymentFormStateModel
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel
import java.util.*

class ExtendableWhiteLabelViewModel(
    engine: PaylikeEngine,
    webView: PaylikeWebView = PaylikeWebView(engine),
    extenderFieldList: List<ExtenderFieldModel> = listOf(),
    onPayButton:
    ((
        cardNumber: String,
        cvc: String,
        expiryMonth: Int,
        expiryYear: Int,
    ) -> Unit),
) : WhiteLabelViewModel(
    engine = engine,
    webView = webView,
    onPayButton = onPayButton,
) {

    /**
     * Variable UI states
     */
//    var extenderPaymentFormState by mutableStateOf(SimplePaymentFormStateModel())
//        private set



    init {
        // TODO Here maybe do the initial ordering of the extender stuff
    }

    override fun resetPaymentFormState() {
        super.resetPaymentFormState()
    }

    override fun onPayButtonClick() {
        super.onPayButtonClick()

        // TODO handle new payment check and pay mechanics
    }

    override fun update(o: Observable?, arg: Any?) {
        super.update(o, arg)

        // TODO handle any new ui stuff
    }
}
