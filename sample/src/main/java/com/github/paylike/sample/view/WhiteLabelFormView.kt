package com.github.paylike.sample.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.paylike.kotlin_luhn.PaylikeLuhn
import com.github.paylike.sample.R
import com.github.paylike.sample.ui.theme.Kotlin_sdkTheme
import com.github.paylike.sample.ui.theme.PaylikeGreen
import com.github.paylike.sample.ui.theme.PaylikeWhite
import com.github.paylike.sample.viewmodel.CardBrands
import com.github.paylike.sample.viewmodel.UIState
import com.github.paylike.sample.viewmodel.WhiteLabelViewModel
import com.steliospapamichail.creditcardmasker.viewtransformations.CardNumberMask
import com.steliospapamichail.creditcardmasker.viewtransformations.ExpirationDateMask

class WhiteLabelActivity : ComponentActivity() {
    private val sampleViewModel: WhiteLabelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhiteLabelFormView(sampleViewModel)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WhiteLabelFormView(viewModel: WhiteLabelViewModel) {
    val uiState = remember { viewModel.uiState }

    Kotlin_sdkTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Scaffold(
                topBar = {
                    TopBarContent()
                },
                content = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(0.dp)
                    ) {
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            CardNumber(
                                uiState.cardNumber,
                                uiState.isCardNumberValid,
                                {
                                    viewModel.setIsCardNumberValid(true)
                                    viewModel.handleCardNumberInputChange(it)
                                    viewModel.setHighlightedCardBrand(it)
                                },
                                Modifier.weight(55f)
                            )
                            Row (
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.weight(45f)
                            )
                            {
                                MasterCardImage(uiState.highlightedCardBrand)
                                VisaImage(uiState.highlightedCardBrand)
                            }
                        }
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Expiration(
                                uiState.expiryDate,
                                uiState.isExpiryDateValid,
                                {
                                    uiState.isExpiryDateValid = true
                                    if (it.length <= 4) uiState.expiryDate = it
                                },
                                Modifier.weight(2f))
                            SecurityCode(
                                uiState.securityCode,
                                uiState.isSecurityCodeValid,
                                {
                                    uiState.isSecurityCodeValid = true
                                    if (it.length <= 3) uiState.securityCode = it
                                },
                                Modifier.weight(1f))
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = PaylikeGreen,
                                contentColor = PaylikeWhite
                            ),
                            onClick = {
                                if (uiState.cardNumber.length < 16 ||
                                    !PaylikeLuhn.isValid(uiState.cardNumber))
                                    uiState.isCardNumberValid = false
                                if (uiState.expiryDate.length < 4 ||
                                    uiState.expiryDate.substring(0, 2).toIntOrNull()!! > 12)
                                    uiState.isExpiryDateValid = false
                                if (uiState.securityCode.length < 3)
                                    uiState.isSecurityCodeValid = false
                            },
                            modifier = Modifier.size(100.dp, 32.dp)
                        ) {
                            Text("Pay", fontSize = 12.sp)
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun TopBarContent() {
    TopAppBar(backgroundColor = PaylikeGreen) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Text(
                "Minimal white label demo",
                color = PaylikeWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
fun CardNumber(
    number: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier,
)
{
    TextField(
        placeholder = { Text(text = "0000 0000 0000 0000") },
        value = number,
        visualTransformation = CardNumberMask(),
        modifier = modifier,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else Color.Red,
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
        modifier = Modifier
            .size(40.dp)
            .padding(horizontal = 8.dp),
        colorFilter = if (highlightedCardBrand == CardBrands.MASTERCARD)
            ColorFilter.tint(Color.LightGray) else null
    )
}

@Composable
private fun MasterCardImage(highlightedCardBrand: CardBrands) {
    Image(
        painter = painterResource(R.drawable.ic_mastercard_icon),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .padding(horizontal = 8.dp),
        colorFilter = if (highlightedCardBrand == CardBrands.VISA)
            ColorFilter.tint(Color.LightGray) else null
    )
}


@Composable
fun Expiration(
    date: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier) {
    TextField(
        placeholder = { Text(text = "MM/YY") },
        value = date,
        visualTransformation = ExpirationDateMask(),
        modifier = modifier,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else Color.Red,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SecurityCode(
    securityCode: String,
    isValid: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier)
{
    TextField(
        placeholder = { Text(text = "XXX") },
        modifier = modifier,
        value = securityCode,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            textColor = if (isValid) Color.Gray else Color.Red,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Kotlin_sdkTheme {
        val sampleViewModel: WhiteLabelViewModel by viewModels()

        WhiteLabelFormView(sampleViewModel)
    }
}
 */
