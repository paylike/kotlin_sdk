package com.github.paylike.kotlin_sdk.paylikeStyle.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_sdk.whitelabel.simple.viewmodel.WhiteLabelViewModel
import com.github.paylike.kotlin_sdk.theme.PaylikeGreen
import com.github.paylike.kotlin_sdk.theme.PaylikeTheme
import com.github.paylike.kotlin_sdk.view.CardNumber
import com.github.paylike.kotlin_sdk.view.CardProviderImage
import com.github.paylike.kotlin_sdk.view.Expiration
import com.github.paylike.kotlin_sdk.view.SecurityCode

/**
 * Whole coherent UI composition
 */
@Composable
fun PaylikeStyleComposable(
    viewModel: WhiteLabelViewModel,
    scaffoldState: ScaffoldState,
) {
    /**
     *
     */
    val uiState = viewModel.paymentFormState

    /**
     *
     */
    val webView = remember { mutableStateOf(viewModel.webView) }

//
//    if (uiState.isSuccess) {
//        Toast.makeText(LocalContext.current, "The transactionId is: ${viewModel.engine.repository.transactionId}", Toast.LENGTH_LONG).show()
//    }
//    if (uiState.isSuccess) {
//        LaunchedEffect(scaffoldState.snackbarHostState) {
//            scaffoldState.snackbarHostState.showSnackbar(
//                message = "The transactionId is: ${viewModel.engine.repository.transactionId}",
//                duration = SnackbarDuration.Long,
//            )
//            viewModel.resetIsSuccess()
//        }
//    }

    /**
     * Wrapped in predefined theme
     */
    PaylikeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

            /**
             * WebView to help TDS flow
             */
            webView.value.WebViewComposable(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(300.dp)
            )



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
                 * Number and icon
                 */
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    CardNumber(
                        uiState.cardNumber,
                        uiState.isCardNumberValid,
                        { viewModel.handleCardInputChange(it) },
                        Modifier.weight(55f)
                    )
                    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.weight(45f)) {
                        CardProviderImage(highlightedCardBrand = uiState.highlightedCardBrand)
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
                        uiState.expiryDate,
                        uiState.isExpiryDateValid,
                        { viewModel.handleExpirationInputChange(it) },
                        Modifier.weight(65f)
                    )
                    SecurityCode(
                        uiState.securityCode,
                        uiState.isSecurityCodeValid,
                        { viewModel.handleSecurityCodeChange(it) },
                        Modifier.weight(35f)
                    )
                }

                /**
                 * Pay button
                 */
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
                        "Pay",
                    )
                }
            }
        }
    }
}
