package com.github.paylike.kotlin_sdk.simplewhitelabel.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.paylike.kotlin_sdk.CardBrands
import com.github.paylike.kotlin_sdk.R
import com.github.paylike.kotlin_sdk.simplewhitelabel.view.theme.*
import com.github.paylike.kotlin_sdk.simplewhitelabel.viewmodel.WhiteLabelViewModel
import com.steliospapamichail.creditcardmasker.viewtransformations.*

@Composable
fun WhiteLabelFormComposable(viewModel: WhiteLabelViewModel) {
    val uiState = viewModel.uiState

    PaylikeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(0.dp)
            ) {
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
                        Modifier.weight(65f)
                    )
                    SecurityCode(
                        uiState.securityCode,
                        uiState.isSecurityCodeValid,
                        { viewModel.handleSecurityCodeChange(it) },
                        Modifier.weight(35f)
                    )
                }
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
                        fontSize = 18.sp,
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
                color = (if (isValid) Color.Gray else PaylikeErrorRed),
                fontSize = 18.sp
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
            Text(
                text = "MM/YY",
                color = (if (isValid) Color.Gray else PaylikeErrorRed),
                fontSize = 18.sp
            )
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
            Text(
                text = "XXX",
                color = (if (isValid) Color.Gray else PaylikeErrorRed),
                fontSize = 18.sp
            )
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
        modifier = Modifier.size(48.dp).padding(horizontal = 8.dp),
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
        modifier = Modifier.size(48.dp).padding(horizontal = 8.dp),
        colorFilter =
            if (highlightedCardBrand == CardBrands.VISA) ColorFilter.tint(Color.LightGray) else null
    )
}
