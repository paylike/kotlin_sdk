package com.github.paylike.kotlin_sdk.whitelabel.simple.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.kotlin_engine.viewmodel.EngineState
import com.github.paylike.kotlin_sdk.*
import com.github.paylike.kotlin_sdk.theme.PaylikeMaterialTheme

/**
 * Whole coherent UI composition
 */
@Composable
fun WhiteLabelComposable(
    viewModel: WhiteLabelViewModel,
    /*theme: MaterialTheme = PaylikeMaterialTheme,*/ // TODO("make it theme parametrised")
) {
    /**
     * Necessary webView to assist the TDS flow.
     * It is responsible to show the catch hints,
     * show challenge,
     * and send challenge response.
     *
     * Contains the logic if it needs to be shown.
     */
    val webView = remember { mutableStateOf(viewModel.webView) }

    /**
     * Debug purposes, shows message that the flow has come to a final state
     *
     * [EngineState.SUCCESS] or [EngineState.SUCCESS]
     */
    if (viewModel.paymentFormState.isFinished) {
        Toast
            .makeText(
                LocalContext.current,
                "Payment flow has finished.",
                Toast.LENGTH_SHORT
            )
            .show()
    }

    /**
     * Wrapped in predefined theme
     */
    PaylikeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            color = MaterialTheme.colors.background
        ) {
            /**
             * Wraps every field
             */
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                /**
                 * WebView to help TDS flow
                 */
                webView.value.WebViewComposable(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(200.dp)
                )
                /**
                 * Form that contains the fields
                 */
                SimpleWhiteLabelFormComposable(
                    modifier = Modifier
                        .fillMaxWidth(),
                    viewModel = viewModel,
                )

                /**
                 * Pay button
                 */
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    PayButton(
                        modifier = Modifier,
                        onClick = { viewModel.onPayButtonClick() },
                        isVisible = viewModel.paymentFormState.isInitialState,
                    )
                }
            }
        }
    }
}

/**
 * Contains the bare minimum fields the payment need to successfully finish.
 *
 * These are:
 *
 * [CardNumberField],
 *
 * [ExpiryDateField],
 *
 * [CardVerificationCodeField],
 *
 * [PayButton].
 */
@Composable
fun SimpleWhiteLabelFormComposable(
    modifier: Modifier = Modifier,
    viewModel: WhiteLabelViewModel,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /**
         * Card number and icon
         */
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            CardNumberField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.paymentFormState.cardNumber,
                isValid = viewModel.paymentFormState.isCardNumberValid,
                isEnabled = viewModel.paymentFormState.isInitialState,
                onValueChanged = { viewModel.onCardNumberChanged(it) },
                highlightedCardProvider = viewModel.paymentFormState.highlightedCardProvider
            )
        }

        /**
         * Expiration date and CVC
         */
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ExpiryDateField(
                modifier = Modifier,
                value = viewModel.paymentFormState.expiryDate,
                isValid = viewModel.paymentFormState.isExpiryDateValid,
                isEnabled = viewModel.paymentFormState.isInitialState,
                onValueChanged = { viewModel.onExpiryDateChanged(it) },
            )
            CardVerificationCodeField(
                modifier = Modifier,
                value = viewModel.paymentFormState.cardVerificationCode,
                isValid = viewModel.paymentFormState.isCardVerificationCodeValid,
                isEnabled = viewModel.paymentFormState.isInitialState,
                onValueChanged = { viewModel.onCardVerificationCodeChanged(it) },
            )
        }
    }
}
