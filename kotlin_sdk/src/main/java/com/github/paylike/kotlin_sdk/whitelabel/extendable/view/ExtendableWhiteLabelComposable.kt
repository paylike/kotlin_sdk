package com.github.paylike.kotlin_sdk.whitelabel.extendable.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_sdk.*
import com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel.ExtendableWhiteLabelViewModel
import com.github.paylike.kotlin_sdk.whitelabel.simple.view.SimpleWhiteLabelFormComposable
import kotlinx.serialization.json.JsonNull.content

@Composable
fun ExtendableWhiteLabelComposable(
    modifier: Modifier = Modifier,
    viewModel: ExtendableWhiteLabelViewModel,
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

    /** Wrapped in predefined theme */
    theme {
        Surface(modifier = modifier.imePadding(), color = MaterialTheme.colors.background) {
            /** Wraps every field */
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                /** WebView to help TDS flow */
                webView.value.WebViewComposable(modifier = Modifier.fillMaxWidth(1f).height(200.dp))

                /** Previous */
                if (viewModel.extenderPaymentFormStateList.isNotEmpty()) {
                    viewModel.extenderPaymentFormStateList.forEach { extenderField ->
                        extenderField.extenderFieldComposable.invoke(
                            modifier = Modifier.fillMaxWidth(),
                            value = extenderField.extenderFieldState.value,
                            textStyle = LocalTextStyle.current,
                            isEnabled = viewModel.paymentFormState.isInitialState,
                            onValueChanged = {
                                extenderField.onExtenderFieldChanged(
                                    it,
                                    extenderField.onChangedPipeLineFunction
                                )
                            },
                        )
                    }
                }

                /** Form that contains the fields and the pay button */
                SimpleWhiteLabelFormComposable(
                    modifier = Modifier.fillMaxWidth(),
                    viewModel = viewModel,
                )

                /** Latter */

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
                    )
                }
            }
        }
    }
}

@Composable
fun ExtenderWrap(
    modifier: Modifier = Modifier,
    content: @Composable (modifier: Modifier) -> Unit,
) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center,
    ) {
        content.invoke(
            modifier = modifier,
        )
    }
}
