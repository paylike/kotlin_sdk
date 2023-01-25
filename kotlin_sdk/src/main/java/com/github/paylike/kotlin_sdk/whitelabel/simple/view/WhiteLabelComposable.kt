package com.github.paylike.kotlin_sdk.whitelabel.simple.view

import android.app.Activity
import android.content.Context.WINDOW_SERVICE
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_sdk.*
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel

/** Whole coherent UI composition */
@Composable
fun WhiteLabelComposable(
    modifier: Modifier = Modifier,
    viewModel: WhiteLabelViewModel,
    theme:
        @Composable
        (
            content: @Composable () -> Unit,
        ) -> Unit =
        { content ->
            MaterialTheme { content.invoke() }
        },
) {
    /**
     * Necessary webView to assist the TDS flow. It is responsible to show the catch hints, show
     * challenge, and send challenge response.
     *
     * Contains the logic if it needs to be shown.
     */
    val webView = remember { mutableStateOf(viewModel.webView) }

    /** Manages the focus changes through the input fields */
    val focusManager = LocalFocusManager.current

    /** Wrapped in predefined theme */
    theme {
        /** Wraps every field */
        Column(
            modifier = modifier.background(color = MaterialTheme.colors.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            /** WebView to help TDS flow */
            val displayMetrics = DisplayMetrics()
            val windowManager = LocalContext.current.getSystemService(WINDOW_SERVICE) as WindowManager
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            webView.value.WebViewComposable(modifier = Modifier.fillMaxWidth(1f).height(
                displayMetrics.heightPixels.dp / displayMetrics.density * 0.8f
            ).focusable())


            /** Form that contains the fields */
            SimpleWhiteLabelFormComposable(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel,
                focusManager = focusManager,
            )

            /** Pay button */
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                PayButton(
                    modifier = Modifier,
                    onClick = { viewModel.onPayButtonClick() },
                    isVisible =
                        viewModel.paymentFormState.isInitialState &&
                            !viewModel.paymentFormState.isPaymentFlowInitiated,
                    focusManager = focusManager,
                )
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
    focusManager: FocusManager,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        /** Card number and icon */
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            CardNumberField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.paymentFormState.cardNumber,
                isValid = viewModel.paymentFormState.isCardNumberValid,
                isEnabled = viewModel.paymentFormState.isInitialState,
                onValueChanged = { viewModel.onCardNumberChanged(it) },
                highlightedCardProvider = viewModel.paymentFormState.highlightedCardProvider,
                focusManager = focusManager,
            )
        }

        /** Expiration date and CVC */
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ExpiryDateField(
                modifier = Modifier,
                value = viewModel.paymentFormState.expiryDate,
                isValid = viewModel.paymentFormState.isExpiryDateValid,
                isEnabled = viewModel.paymentFormState.isInitialState,
                onValueChanged = { viewModel.onExpiryDateChanged(it) },
                focusManager = focusManager,
            )
            CardVerificationCodeField(
                modifier = Modifier,
                value = viewModel.paymentFormState.cardVerificationCode,
                isValid = viewModel.paymentFormState.isCardVerificationCodeValid,
                isEnabled = viewModel.paymentFormState.isInitialState,
                onValueChanged = { viewModel.onCardVerificationCodeChanged(it) },
                focusManager = focusManager,
            )
        }
    }
}
