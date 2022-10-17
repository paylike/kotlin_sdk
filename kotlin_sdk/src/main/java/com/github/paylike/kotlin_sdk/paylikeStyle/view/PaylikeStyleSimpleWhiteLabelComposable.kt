package com.github.paylike.kotlin_sdk.paylikeStyle.view

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_sdk.SecurePaymentLabel
import com.github.paylike.kotlin_sdk.SuccessAnimation
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.whitelabel.simple.view.WhiteLabelComposable
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel

/** Whole coherent UI composition */
@Composable
fun PaylikeStyleSimpleWhiteLabelComposable(
    modifier: Modifier = Modifier,
    viewModel: WhiteLabelViewModel,
) {
    Column( // TODO to column the whole shite?
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (viewModel.paymentFormState.isFinished) {
            SuccessAnimation(
                modifier = Modifier.fillMaxWidth().aspectRatio(2f),
            )
        }
        if (!viewModel.paymentFormState.isFinished) {
            WhiteLabelComposable(
                viewModel = viewModel,
                theme = { content -> run { PaylikeTheme { content.invoke() } } }
            )
        }
        if (!viewModel.paymentFormState.isFinished && !viewModel.paymentFormState.isInitialState) {
            Text(text = "spinner shit")
        }
        SecurePaymentLabel(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            textStyle = PaylikeTheme.typography.caption,
        )
    }
}
