package com.github.paylike.kotlin_sdk.simplewhitelabel.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.paylike.kotlin_sdk.CardBrands
import com.github.paylike.kotlin_sdk.R
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.*
import com.github.paylike.kotlin_sdk.simplewhitelabel.viewmodel.WhiteLabelViewModel
import com.steliospapamichail.creditcardmasker.viewtransformations.*

@Composable
fun WhiteLabelFormComposable(
    viewModel: WhiteLabelViewModel,
    scaffoldState: ScaffoldState,
) {
    val uiState = viewModel.uiState
    val webView = remember { mutableStateOf(viewModel.webView) }
    val isSuccess = remember { mutableStateOf(uiState.isSuccess) }

    if (uiState.isSuccess) {
        Toast.makeText(LocalContext.current, "The transactionId is: ${viewModel.engine.repository.transactionId}", Toast.LENGTH_LONG).show()
    }
    if (uiState.isSuccess) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "The transactionId is: ${viewModel.engine.repository.transactionId}",
                duration = SnackbarDuration.Long,
            )
            viewModel.resetIsSuccess()
        }
    }

    PaylikeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(0.dp)
            ) {
                webView.value.WebViewComposable(
                    modifier = Modifier.fillMaxWidth(1f).height(300.dp)
                )
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
                        MasterCardImage(uiState.highlightedCardBrand)
                        VisaImage(uiState.highlightedCardBrand)
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Expiration(
                        uiState.expiryDate,
                        uiState.isExpiryDateValid,
                        { viewModel.handleExpirationInputChange(it) },
                        Modifier.weight(2f)
                    )
                    SecurityCode(
                        uiState.securityCode,
                        uiState.isSecurityCodeValid,
                        { viewModel.handleSecurityCodeChange(it) },
                        Modifier.weight(1f)
                    )
                }
                Button(
                    colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = PaylikeGreen,
                        contentColor = Color.White
                    ),
                    onClick = { viewModel.handleButtonClick() },
                    modifier = Modifier.size(100.dp, 32.dp)
                ) {
                    Text(
                        "Pay",
                    )
                }
            }
        }
    }
}

@Composable
private fun CardNumber(
    number: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
) {
    TextField(
        placeholder = {
            Text(
                text = "0000 0000 0000 0000",
                color = (if (isValid) Color.Gray else PaylikeErrorRed)
            )
        },
        value = number,
        visualTransformation = CardNumberMask(),
        modifier = modifier,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else PaylikeErrorRed,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun Expiration(
    date: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        placeholder = {
            Text(text = "MM/YY", color = (if (isValid) Color.Gray else PaylikeErrorRed))
        },
        value = date,
        visualTransformation = ExpirationDateMask(),
        modifier = modifier,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else PaylikeErrorRed,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun SecurityCode(
    securityCode: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier
) {
    TextField(
        placeholder = {
            Text(text = "XXX", color = (if (isValid) Color.Gray else PaylikeErrorRed))
        },
        modifier = modifier,
        value = securityCode,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors =
        TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else PaylikeErrorRed,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
private fun VisaImage(highlightedCardBrand: CardBrands) {
    Image(
        painter = painterResource(R.drawable.ic_visa_icon),
        contentDescription = null,
        modifier = Modifier.size(40.dp).padding(horizontal = 8.dp),
        colorFilter =
        if (highlightedCardBrand == CardBrands.MASTERCARD) ColorFilter.tint(Color.LightGray)
        else null
    )
}

@Composable
private fun MasterCardImage(highlightedCardBrand: CardBrands) {
    Image(
        painter = painterResource(R.drawable.ic_mastercard_icon),
        contentDescription = null,
        modifier = Modifier.size(40.dp).padding(horizontal = 8.dp),
        colorFilter =
        if (highlightedCardBrand == CardBrands.VISA) ColorFilter.tint(Color.LightGray) else null
    )
}
