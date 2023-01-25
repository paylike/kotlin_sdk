package com.github.paylike.kotlin_sdk.paylikeStyle.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_sdk.*
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.whitelabel.extendable.view.ExtendableWhiteLabelComposable
import com.github.paylike.kotlin_sdk.whitelabel.extendable.viewmodel.ExtendableWhiteLabelViewModel

/** Whole coherent UI composition */
@Composable
fun PaylikeStyleExtendableWhiteLabelComposable(
    modifier: Modifier = Modifier,
    viewModel: ExtendableWhiteLabelViewModel,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PaylikeTheme {
            if (viewModel.paymentFormState.isSuccess) {
                SuccessAnimation(
                    modifier = Modifier.fillMaxWidth().aspectRatio(2f),
                )
            }
            if (viewModel.paymentFormState.isError) {
                ErrorLog(
                    modifier = Modifier.fillMaxWidth(),
                    error = viewModel.paymentFormState.error!!,
                )
            }
            if (!viewModel.paymentFormState.isSuccess) {
                ExtendableWhiteLabelComposable(
                    viewModel = viewModel,
                    theme = { content -> run { PaylikeTheme() { content.invoke() } } },
                )
            }
            if (viewModel.paymentFormState.isPaymentFlowInitiated) {
                LoadingSpinner(
                    modifier = Modifier.size(32.dp),
                    color = PaylikeTheme.colors.primary,
                )
            }
            SecurePaymentLabel(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                textStyle = PaylikeTheme.typography.caption,
            )
        }
    }
}
