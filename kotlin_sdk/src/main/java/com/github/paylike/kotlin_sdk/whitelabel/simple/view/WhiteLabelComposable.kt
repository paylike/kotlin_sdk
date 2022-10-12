package com.github.paylike.kotlin_sdk.whitelabel.simple.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel
import com.github.paylike.kotlin_sdk.theme.PaylikeGreen
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.view.CardNumber
import com.github.paylike.kotlin_sdk.view.CardProviderImage
import com.github.paylike.kotlin_sdk.view.Expiration
import com.github.paylike.kotlin_sdk.view.SecurityCode
import com.github.paylike.kotlin_sdk.R

/**
 * Whole coherent UI composition
 */
@Composable
fun WhiteLabelComposable(
    viewModel: WhiteLabelViewModel,
    scaffoldState: ScaffoldState,
) {
    /**
     *
     */
    val webView = remember { mutableStateOf(viewModel.webView) }

//
//    if (uiState.isSuccess) {
//        Toast.makeText(LocalContext.current, "The transactionId is: ${viewModel.engine.repository.transactionId}", Toast.LENGTH_LONG).show()
//    }
    if (viewModel.paymentFormState.isFinished) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Finished",
                duration = SnackbarDuration.Long,
            )
            viewModel.resetPaymentFormState()
        }
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
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
            ) {

                /**
                 * WebView to help TDS flow
                 */
                webView.value.WebViewComposable(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .height(300.dp)
                )

                /**
                 * Number and icon
                 */
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    CardNumber(
                        viewModel.paymentFormState.cardNumber,
                        viewModel.paymentFormState.isCardNumberValid,
                        { viewModel.handleCardInputChange(it) },
                        Modifier.weight(55f)
                    )
                    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.weight(45f)) {
                        CardProviderImage(highlightedCardBrand = viewModel.paymentFormState.highlightedCardBrand)
                    }
                }

                /**
                 * Expiration date and cvc
                 */
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Expiration(
                        viewModel.paymentFormState.expiryDate,
                        viewModel.paymentFormState.isExpiryDateValid,
                        { viewModel.handleExpirationInputChange(it) },
                        Modifier.weight(65f)
                    )
                    SecurityCode(
                        viewModel.paymentFormState.securityCode,
                        viewModel.paymentFormState.isSecurityCodeValid,
                        { viewModel.handleSecurityCodeChange(it) },
                        Modifier.weight(35f)
                    )
                }

                /**
                 * Pay button
                 */
                if (viewModel.paymentFormState.isPayButtonVisible) {
                    Button(
                        colors =
                        ButtonDefaults.buttonColors(
                            backgroundColor = PaylikeGreen,
                            contentColor = Color.White
                        ),
                        onClick = { viewModel.handleButtonClick() },
                        modifier = Modifier.size(144.dp, 48.dp),
                        shape = RoundedCornerShape(12)
                    ) {
                        Text(
                            LocalContext.current.getString(R.string.PayButton),
                        )
                    }
                }
            }
        }
    }
}
