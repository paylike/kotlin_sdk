package com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
    val extenderPaymentFormStateList by mutableStateOf(extenderFieldList) // TODO Maybe map?
    //        private set

    init {
        extenderPaymentFormStateList.forEach { extenderField ->
            extenderField.onExtenderFieldChanged = {}

            //            extenderField.extenderFieldComposable = {
            //                extenderField.extenderFieldComposable.invoke(
            //
            //                )
            //            }
        }
    }

    override fun resetPaymentFormState() {
        extenderPaymentFormStateList.forEach { extenderField ->
            extenderField.extenderFieldState = ExtenderFieldState()
        }
        super.resetPaymentFormState()
    }

    override fun onPayButtonClick() {
        val fieldList: MutableList<String> = mutableListOf()
        extenderPaymentFormStateList.forEach { extenderField ->
            fieldList.add(extenderField.extenderFieldState.field)
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
        //        extenderPaymentFormStateList.forEach { extenderField -> // TODO do we have to
        // check anything?
        //            extenderField.extenderFieldState.field
        //        }
        // It invokes a empty callback from the simple white label, but doesn't matter (?)

        // TODO handle new payment check and pay mechanics
    }

    override fun update(o: Observable?, arg: Any?) {
        super.update(o, arg)

        // TODO handle any new ui stuff
    }
}
